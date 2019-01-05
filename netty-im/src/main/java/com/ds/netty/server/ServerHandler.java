package com.ds.netty.server;

import com.ds.netty.protocol.Packet;
import com.ds.netty.protocol.PacketCodeC;
import com.ds.netty.protocol.request.LoginRequestPacket;
import com.ds.netty.protocol.request.MessageRequestPacket;
import com.ds.netty.protocol.response.LoginResponsePacket;
import com.ds.netty.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

/**
 * @author duosheng
 * @since 2019/1/4
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket) {
            System.out.println(LocalDateTime.now() + ": 收到客户端登录请求……");

            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
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
        } else if (packet instanceof MessageRequestPacket) {
            // 客户端发来消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            System.out.println(LocalDateTime.now() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(encode);
        }


    }

    private boolean valid(LoginRequestPacket  loginRequestPacket) {
        return true;
    }
}
