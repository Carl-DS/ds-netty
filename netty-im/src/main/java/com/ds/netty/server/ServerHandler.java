package com.ds.netty.server;

import com.ds.netty.protocol.Packet;
import com.ds.netty.protocol.PacketCodeC;
import com.ds.netty.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author duosheng
 * @since 2019/1/4
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(LocalDateTime.now() + ": 客户端开始登录……");
        ByteBuf requestByteBuf  = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        if (valid(loginResponsePacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(LocalDateTime.now() + ": 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(LocalDateTime.now() + ": 登录失败!");
        }

        // 登录响应
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
    }

    private boolean valid(LoginResponsePacket loginResponsePacket) {
        return true;
    }
}
