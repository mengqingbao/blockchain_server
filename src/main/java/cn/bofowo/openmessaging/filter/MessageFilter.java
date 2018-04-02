package cn.bofowo.openmessaging.filter;

public interface MessageFilter {
	public void doFilter(MessageFilterCondition datas,MessageFilterChain chain);
}
