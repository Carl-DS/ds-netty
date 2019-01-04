package com.ds.netty.protocol.request;

import com.ds.netty.protocol.Packet;
import com.ds.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author duosheng
 * @since 2019/1/2
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    /**
     * 指令
     * 所有的指令数据包都必须实现这个方法,这样我们就可以知道某种指令的含义
     */
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
