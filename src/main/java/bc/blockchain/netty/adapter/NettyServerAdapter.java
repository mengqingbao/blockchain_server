package bc.blockchain.netty.adapter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetSocketAddress;
import java.util.Date;

import bc.blockchain.callback.impl.SimpleCallBack;
import bc.blockchain.common.request.Request;
import bc.blockchain.common.request.RequestType;
import bc.blockchain.common.response.Response;
import bc.blockchain.peer.Peer;
import bc.blockchain.server.BlockChainContext;
import bc.blockchain.util.DispatherUtil;
import bc.blockchain.util.RequestUtil;

@Sharable
public class NettyServerAdapter extends ChannelInboundHandlerAdapter {
	private BlockChainContext blockChainContext;

	public NettyServerAdapter(BlockChainContext blockChainContext) {
		this.blockChainContext = blockChainContext;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		Request req = RequestUtil.create(msg.toString());
		SocketChannel clientChannel = (SocketChannel) ctx.channel();
		InetSocketAddress isd = clientChannel.remoteAddress();
		Peer peer = createPeer(isd.getHostString(), isd.getPort());
		SimpleCallBack callback=new SimpleCallBack(blockChainContext,req.getrequestType());
		callback.setPeer(peer);;
		callback.setRequest(req);
		Response response= new DispatherUtil(callback).doService(req);
		Channel channel=ctx.channel();
		channel.writeAndFlush(response.toString());
		if(req.getrequestType()==RequestType.BYE){
			channel.close();
		}
	}


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {

			IdleStateEvent event = (IdleStateEvent) evt;

			if (event.state().equals(IdleState.READER_IDLE)) {
				// 未进行读操作
				//System.out.println("READER_IDLE");
				// 超时关闭channel
				// ctx.close();

			} else if (event.state().equals(IdleState.WRITER_IDLE)) {

			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				// 发送心跳消息
				//System.out.println("系统通信状态"+event.state());

			}

		}
		super.userEventTriggered(ctx, evt);

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// nettyServerContext.removeChannelFromLiveMap(ctx.channel().id().asShortText());
		super.channelInactive(ctx);
	}

	public void regChannel(String key, SocketChannel channel) {
	}

	public void attachTopic(String topic, String queue) {
	}

	private Peer createPeer(String addr, Integer port) {
		Peer peer = new Peer(addr, port, new Date());
		peer.setAddress(addr);
		peer.setPort(port);
		peer.setLiveTime(new Date());
		return peer;

	}

}
