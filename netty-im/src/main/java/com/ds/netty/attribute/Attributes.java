package com.ds.netty.attribute;

import io.netty.util.AttributeKey;

/**
 * @author duosheng
 * @since 2019/1/4
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
