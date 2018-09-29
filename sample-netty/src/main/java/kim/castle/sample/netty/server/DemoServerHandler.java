package kim.castle.sample.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DemoServerHandler extends SimpleChannelInboundHandler<Object> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Client say : " + msg.toString());
		// 返回客户端消息 - 我已经接收到了你的消息
		ctx.writeAndFlush("Received your message : " + msg.toString());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
		ctx.writeAndFlush("连接成功！");
		super.channelActive(ctx);
	}
}