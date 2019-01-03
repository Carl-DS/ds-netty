package com.ds.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.ds.netty.serialize.Serializer;
import com.ds.netty.serialize.SerializerAlgorithm;

/**
 * @author duosheng
 * @since 2019/1/3
 */
public class JSONSerializer implements Serializer {

    /**
     * 序列化算法
     */
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @return
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
