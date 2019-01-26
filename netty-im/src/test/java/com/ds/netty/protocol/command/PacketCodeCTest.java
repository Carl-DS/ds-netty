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

import java.util.UUID;

/**
 * @author duosheng
 * @since 2019/1/4
 */
public class PacketCodeCTest {

    @Test
    public void encode() {
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = PacketCodeC.INSTANCE;
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        packetCodeC.encode(byteBuf, loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assertions.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }

    @Test
    public void decode() {
    }
}
