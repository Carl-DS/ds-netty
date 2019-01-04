package com.ds.netty.protocol.response;

import com.ds.netty.protocol.Packet;
import lombok.Data;

import static com.ds.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author duosheng
 * @since 2019/1/4
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    /**
     * 指令
     * 所有的指令数据包都必须实现这个方法,这样我们就可以知道某种指令的含义
     */
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
