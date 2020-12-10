package io.seata.samples.tcc.transfer.starter;

import io.seata.samples.tcc.transfer.ApplicationKeeper;
import io.seata.samples.tcc.transfer.env.TransferDataPrepares;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 转账TCC Dubbo服务 提供方
 *
 * @author zhangsen
 */
public class TransferProviderStarter {


    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            new String[] {"spring/seata-tcc.xml", "spring/seata-dubbo-provider.xml",
                "db-bean/to-datasource-bean.xml", "db-bean/from-datasource-bean.xml"});

        //初始化数据库和账号余额
        TransferDataPrepares transferDataPrepares = (TransferDataPrepares) applicationContext.getBean("transferDataPrepares");
        transferDataPrepares.init(100);

        new ApplicationKeeper(applicationContext).keep();
    }


}
