package com.ds.netty.codec;

import com.ds.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author duosheng
 * @since 2019/1/26
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    /**
     * 设计魔数的原因是为了尽早屏蔽非本协议的客户端，通常在第一个 handler 处理这段逻辑。
     * 接下来的做法是每个客户端发过来的数据包都做一次快速判断，判断当前发来的数据包是否是满足我的自定义协议，
     * 只需要继承自 LengthFieldBasedFrameDecoder 的 decode() 方法，
     * 然后在 decode 之前判断前四个字节是否是等于我们定义的魔数 0x12345678
     *
     * 这里的 decode() 方法中，第二个参数 in，每次传递进来的时候，均为一个数据包的开头
     *
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
