package com.ds.netty.protocol.response;

import com.ds.netty.protocol.Packet;
import com.ds.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author duosheng
 * @since 2019/1/4
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    /**
     * 指令
     * 所有的指令数据包都必须实现这个方法,这样我们就可以知道某种指令的含义
     *
     * @return
     */
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
