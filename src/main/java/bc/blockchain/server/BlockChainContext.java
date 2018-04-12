package bc.blockchain.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bc.blockchain.netty.BcServer;
import bc.blockchain.peer.Peer;
import bc.blockchain.util.DateUtil;

public class BlockChainContext {
	private Logger logger=LoggerFactory.getLogger(getClass());
	private final static Hashtable<String, Peer> peerTable = new Hashtable<String, Peer>();
	private ScheduledExecutorService scheduledThreadPool = Executors
			.newScheduledThreadPool(1);
	private BcServer server;

	public void initServer() {
		if (server == null) {
			server = new BcServer(this);

		}
	}

	public void start() {
		setSysEvn();

		startScheduleCheckLivePeer();

		if (server == null) {
			initServer();
		}

		try {
			server.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				shutdown();
				logger.info("shutdownhook");
			}

		}));

		logger.info("启动完成");
	}

	// 启动定时器监测客户端是否存货。通知客户端是否在线。
	private void startScheduleCheckLivePeer() {
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				logger.info("Peer端数量&列表：" + peerTable.size());
				// 获取到Map中所有的key ，key被放到了一个set集合中
				// 获取到所有的key集合的 迭代器
				List<Peer> waitingDelPeerList = new ArrayList();
				for (String key : peerTable.keySet()) {
					Peer peer = peerTable.get(key);
					logger.info(peer.toString());
					if (peer.getLiveTime().before(
							DateUtil.addSecond(new Date(), -30))) {
						waitingDelPeerList.add(peer);
						logger.info("客户端离线，进入删除队列" + peer.toString()
								+ ",等待删除。");
					}
				}
				for(Peer peer:waitingDelPeerList){
					peerTable.remove(peer.genId());
				}
			}
		}, 3, 5, TimeUnit.SECONDS);

	}

	// 关闭服务端
	public void shutdown() {
		scheduledThreadPool.shutdown();
	}

	// 设置环境变量
	private void setSysEvn() {

	}

	public void regClient(Peer peer) {
		peerTable.put(peer.genId(), peer);
	}

	public void freshClient(Peer peer) {
		Peer peer2 = peerTable.get(peer.genId());
		if (peer2 != null) {
			peer2.setLiveTime(new Date());
			peerTable.put(peer2.genId(), peer2);
			logger.info("更新服务器内容");
		}
	}

}
