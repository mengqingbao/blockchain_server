package cn.bofowo.openmessaging.message;

import cn.bofowo.openmessaging.util.StringUtil;

public enum MessageType {
	REG("REG"),DISTORY("DISTORY"),HEARTBEAT("HEARTBEAT"), COMMON("COMMON"), EXCEPTION("EXCEPTION");
	private String type;

	MessageType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static MessageType getByCode(String code) {
		if (StringUtil.isEmpty(code)) {
			return getByCode("COMMON");
		}
		for (MessageType type : MessageType.values()) {
			if (type.getType().equals(code)) {
				return type;
			}
		}
		throw new IllegalArgumentException("非法的枚举参数：" + code);
	}

}
