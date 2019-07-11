package com.hc.ml.feature.engineering.storage;

import com.hc.ml.feature.engineering.utils.Bytes;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProtobufferObjSerializer
 *
 * @author han.congcong
 * @date 2019/7/8
 */

public class ProtobufferObjSerializer implements IObjSerializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtobufferObjSerializer.class);
    private static final String UTF_8= "utf-8";
    private LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    @Override
    public byte[] serialize(List list) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Object o : list) {
            String className = o.getClass().getName();
            byte[] classNameBytes = className.getBytes(UTF_8);
            Schema schema = RuntimeSchema.getSchema(o.getClass());
            byte[] data;
            try {
                data = ProtostuffIOUtil.toByteArray(o, schema, buffer);
            } finally {
                buffer.clear();
            }
            int classNameBytesLength = classNameBytes.length;
            int dataLength = data.length;
            outputStream.write(Bytes.toBytes(classNameBytesLength));
            outputStream.write(Bytes.toBytes(dataLength));
            outputStream.write(classNameBytes);
            outputStream.write(data);
        }
        return outputStream.toByteArray();
    }

    @Override
    public List<Object> unSerialize(byte[] bytes) throws UnsupportedEncodingException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int index = 0;
        List<Object> rets = new ArrayList<>();
        while(true){
            if(index == bytes.length){
                break;
            }
            byte[] classNameLengthBytes = new byte[4];
            System.arraycopy(bytes,index,classNameLengthBytes,0,4);
            index = index + 4;
            byte[] dataLengthBytes = new byte[4];
            System.arraycopy(bytes,index,dataLengthBytes,0,4);
            index = index + 4;
            int classNameLength = Bytes.toInt(classNameLengthBytes);
            int dataLength = Bytes.toInt(dataLengthBytes);
            byte[] classNameBytes = new byte[classNameLength];
            System.arraycopy(bytes,index,classNameBytes,0,classNameBytes.length);
            index = index + classNameBytes.length;
            byte[] data = new byte[dataLength];
            System.arraycopy(bytes,index,data,0,data.length);
            index = index + data.length;
            String className = new String(classNameBytes,UTF_8);
            Class clazz = Class.forName(className);
            Schema schema = RuntimeSchema.getSchema(clazz);
            Object o = clazz.newInstance();
            ProtostuffIOUtil.mergeFrom(data,o,schema);
            rets.add(o);
        }

        return rets;
    }
}
