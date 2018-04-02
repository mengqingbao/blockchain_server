package cn.bofowo.openmessaging.netty.uri;

import io.netty.channel.Channel;

public class URI {
	private String ip;
	private int port;
	private Channel channel;
	private boolean able;
	public URI(String ip, int port) {
		this.ip=ip;
		this.port=port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public boolean isAble() {
		return able;
	}
	public void setAble(boolean able) {
		this.able = able;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		
		URI uri=(URI) obj;
		if(uri.getIp().equals(this.ip)&&uri.getPort()==this.getPort()){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
}
