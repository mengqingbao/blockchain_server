package bc.blockchain.server;

import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bc.blockchain.netty.BcServer;
import bc.blockchain.peer.Peer;

public class BlockChainContext {

	private final static Hashtable<String, Peer> peerTable = new Hashtable<String, Peer>();
	private ScheduledExecutorService scheduledThreadPool = Executors
			.newScheduledThreadPool(1);
	private BcServer server;
	public void regPeer(String addr, Peer peer) {
		peerTable.put(addr, peer);
	}

	public void initServer(){
		if(server==null){
			server=new BcServer();
			
		}
	}
	public void start() {
		setSysEvn();
		
		startScheduleCheckLivePeer();
		
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){

			@Override
			public void run() {
				shutdown();
				System.out.println("shutdownhook");
			}
			
			
		}));
		
		System.out.println("启动完成");
	}

	//启动定时器监测客户端是否存货。通知客户端是否在线。
	private void startScheduleCheckLivePeer() {
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, 5, TimeUnit.SECONDS);
		
	}

	//关闭服务端
	public void shutdown(){
		scheduledThreadPool.shutdown();
	}
	
	//设置环境变量
	private void setSysEvn(){
		
		
	}

}
