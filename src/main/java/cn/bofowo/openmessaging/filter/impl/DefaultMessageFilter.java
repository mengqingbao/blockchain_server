package cn.bofowo.openmessaging.filter.impl;

import cn.bofowo.openmessaging.filter.MessageFilter;
import cn.bofowo.openmessaging.filter.MessageFilterChain;
import cn.bofowo.openmessaging.filter.MessageFilterCondition;

public class DefaultMessageFilter implements MessageFilter {

	@Override
	public void doFilter(MessageFilterCondition datas, MessageFilterChain chain) {
		chain.doFilter(datas);
	}

}
