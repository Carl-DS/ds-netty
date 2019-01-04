package com.ds.netty.serialize;

import com.ds.netty.serialize.impl.JSONSerializer;

/**
 * 如果想要实现其他序列化算法的话,只需要继承一下Serializer, 然后定义一下序列化算法的标识,
 * 再覆盖一下两个方法即可
 * @author duosheng
 * @since 2019/1/2
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
