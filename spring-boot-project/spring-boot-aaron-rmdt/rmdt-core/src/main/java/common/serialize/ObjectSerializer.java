package core.serialize;


/**
 * @author luohaipeng
 * 对象序列化
 */
public interface ObjectSerializer {


    byte[] serialize(Object obj)throws Exception;


    <T> T deSerialize(byte[] param, Class<T> clazz)throws Exception;

}
