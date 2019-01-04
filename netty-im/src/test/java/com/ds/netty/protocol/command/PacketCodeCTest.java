package com.ds.netty.protocol.command;


import com.ds.netty.protocol.Packet;
import com.ds.netty.protocol.PacketCodeC;
import com.ds.netty.protocol.request.LoginRequestPacket;
import com.ds.netty.serialize.Serializer;
import com.ds.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author duosheng
 * @since 2019/1/4
 */
public class PacketCodeCTest {

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("carl");
        loginRequestPacket.setPassword("carl1304");
        loginRequestPacket.setUserId("1304");
        loginRequestPacket.setVersion((byte)1);


        ByteBuf encode = PacketCodeC.INSTANCE.encode(ByteBufAllocator.DEFAULT, loginRequestPacket);
        Packet decode = PacketCodeC.INSTANCE.decode(encode);
        Assertions.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decode));
    }

    @Test
    public void decode() {
    }
}
