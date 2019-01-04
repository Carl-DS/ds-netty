package com.ds.netty.protocol.command;

/**
 * @author duosheng
 * @since 2019/1/2
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE  = 4;
}
