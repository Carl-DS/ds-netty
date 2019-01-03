package com.ds.netty.protocol.command;

import lombok.Data;

/**
 * @author duosheng
 * @since 2019/1/2
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * 所有的指令数据包都必须实现这个方法,这样我们就可以知道某种指令的含义
     */
    public abstract Byte getCommand();
}
