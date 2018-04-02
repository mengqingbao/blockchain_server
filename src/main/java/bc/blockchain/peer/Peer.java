package bc.blockchain.peer;

import java.util.Date;

public class Peer {
	
	private String address;
	private String ip;
	private Integer port;
	private Date liveTime;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Date getLiveTime() {
		return liveTime;
	}
	public void setLiveTime(Date liveTime) {
		this.liveTime = liveTime;
	}
	
	

}
