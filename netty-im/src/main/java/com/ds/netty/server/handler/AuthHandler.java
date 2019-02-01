package com.ds.netty.server.handler;

import com.ds.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * AuthHandler 继承自 ChannelInboundHandlerAdapter，覆盖了 channelRead() 方法，表明他可以处理所有类型的数据
 * <p>
 * 在客户端校验通过之后，我们不再需要 AuthHandler 这段逻辑，而这一切只需要一行代码即可实现：
 *
 * @author duosheng
 * @since 2019/2/1
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 一行代码实现逻辑的删除
            // 这里的 this 指的其实就是 AuthHandler 这个对象，删除之后，这条客户端连接的逻辑链中就不再有这段逻辑了
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕,无需再次验证,AuthHandler 被移除");
        } else {
            System.out.println("无需登录验证,强制关闭连接!");
        }
    }
}
