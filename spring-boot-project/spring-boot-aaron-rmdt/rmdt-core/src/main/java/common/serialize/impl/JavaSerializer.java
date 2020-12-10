package core.serialize.impl;


import core.serialize.ObjectSerializer;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 使用jdk自带的序列化工具
 */
@Service
public class JavaSerializer implements ObjectSerializer {

    @Override
    public byte[] serialize(final Object obj) throws Exception {
        try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(); ObjectOutput objectOutput = new ObjectOutputStream(arrayOutputStream)) {
            objectOutput.writeObject(obj);
            objectOutput.flush();
            return arrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new Exception("java serialize error " + e.getMessage());
        }
    }

    @Override
    public <T> T deSerialize(final byte[] param, final Class<T> clazz) throws Exception {
        try (ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(param); ObjectInput input = new ObjectInputStream(arrayInputStream)) {
            return (T) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("java deSerialize error " + e.getMessage());
        }
    }

}
