package dubbo.proxy;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import com.alibaba.fastjson.JSON;
import com.shirc.redis.delay.queue.core.bootstrap.ApplicationContextHolder;
import com.shirc.redis.delay.queue.core.coordinator.RmdtTransactionCoordinatorSupport;
import lombok.extern.slf4j.Slf4j;
import org.rmdt.annotation.Rmdt;
import org.rmdt.common.constant.RmdtConstant;
import org.rmdt.common.context.RmdtTransactionContext;
import org.rmdt.common.domain.Participant;
import org.rmdt.common.domain.RPCErrorInfo;
import org.rmdt.common.thread.RmdtTransactionThreadLocal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author luohaipeng
 * 包装的Dubbo的InvokerInvocationHandler的invoke方法
 * 对开启了分布式事务的远程方法做如下处理：
 * 1、dubbo抛出的异常自己处理，不往外抛，这样就能确保事务方法正常执行完，不会中断，最后发送可靠MQ消息（可以把异常信息记录起来）
 * 2、如果本地存在事务上下文对象，则往放到RPC接口参数中，传到远程方法
 * 3、为当前事务添加一个事务参与者，参与者为这个被调用的远程方法
 */
@Slf4j
public class RmdtInvokerInvocationHandler extends InvokerInvocationHandler {

    private Object target;


    public RmdtInvokerInvocationHandler(final Invoker<?> handler) {
        super(handler);
    }

    public <T> RmdtInvokerInvocationHandler(final T target, final Invoker<T> handler) {
        super(handler);
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Rmdt rmdt = method.getAnnotation(Rmdt.class);
        if (Objects.nonNull(rmdt)) {
            try {
                log.debug("1、进入dubbo自定义Handler，调用"+method.getName()+"方法");
                //通过ThreadLocal获取当前事务上下文对象
                RmdtTransactionContext rmdtTransactionContext = RmdtTransactionThreadLocal.CONTEXT_THREADLOCAL.get();
                //如果当前事务上下文对象不为null，则进行以下操作
                if(Objects.nonNull(rmdtTransactionContext)){
                    //把事务上下文对象作为RPC接口参数，传递到远程方法
                    RpcContext.getContext().setAttachment(RmdtConstant.RMDT_TRANSACTION_CONTEXT,JSON.toJSONString(rmdtTransactionContext));
                    log.debug("2、把事务参与者上下文对象作为RPC接口参数，传递到远程方法"+ JSON.toJSONString(rmdtTransactionContext));
                    //为当前事务添加一个事务参与者，参与者为这个被调用的远程方法
                    Participant participant = new Participant();
                    participant.setTransactionId(rmdtTransactionContext.getTransactionId());
                    participant.setMessageDomain(rmdt.messageDomain().getCode());
                    participant.setDestination(rmdt.destination());
                    participant.setTargetClass(method.getDeclaringClass());
                    participant.setMethodName(method.getName());
                    participant.setParameterTypes(method.getParameterTypes());
                    participant.setArguments(args);
                    RmdtTransactionCoordinatorSupport rmdtTransactionSupport = ApplicationContextHolder.getBean(RmdtTransactionCoordinatorSupport.class);
                    rmdtTransactionSupport.addParticipant(participant);
                    log.debug("3、添加事务参与者："+participant.getTargetClass()+"."+participant.getMethodName());
                }
                return super.invoke(target, method, args);
            } catch (Throwable t) {
                log.error(t.getMessage());
                //把错误消息记录起来
                RPCErrorInfo rpcErrorInfo = new RPCErrorInfo();
                rpcErrorInfo.setCode(1);
                rpcErrorInfo.setErrorMsg(t.getMessage());
                RmdtTransactionThreadLocal.RPC_ERROR_INFO_THREADLOCAL.set(rpcErrorInfo);
                return returnDefaultValue(method.getReturnType());
            }
        } else {
            return super.invoke(target, method, args);
        }
    }

    public Object returnDefaultValue(final Class clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if (boolean.class.equals(clazz) || Boolean.class.equals(clazz)) {
            return false;
        } else if (byte.class.equals(clazz) || Byte.class.equals(clazz)) {
            return 0;
        } else if (short.class.equals(clazz) || Short.class.equals(clazz)) {
            return 0;
        } else if (int.class.equals(clazz) || Integer.class.equals(clazz)) {
            return 0;
        } else if (long.class.equals(clazz) || Long.class.equals(clazz)) {
            return 0L;
        } else if (float.class.equals(clazz) || Float.class.equals(clazz)) {
            return 0.0f;
        } else if (double.class.equals(clazz) || Double.class.equals(clazz)) {
            return 0.0d;
        } else if (String.class.equals(clazz)) {
            return "";
        } else if (Void.TYPE.equals(clazz)) {
            return "";
        }
        final Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[constructors.length - 1];
        constructor.setAccessible(true);
        final Class[] parameClasses = constructor.getParameterTypes();
        Object[] args = new Object[parameClasses.length];
        for (int i = 0; i < parameClasses.length; i++) {
            Class clazzes = parameClasses[i];
            if (clazzes.isPrimitive()) {
                args[i] = 0;
            } else {
                args[i] = null;
            }
        }
        return constructor.newInstance(args);
    }
}
