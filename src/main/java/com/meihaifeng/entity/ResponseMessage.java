package com.meihaifeng.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 包装返回给客户端数据的对象
 * @author meihf
 *
 */
public class ResponseMessage implements Serializable {

	public  ResponseMessage(){};

	private Map<String,Object> message;//消息内容 <消息标题，消息结果>
	private boolean success;//做某事是否成功

	public Map<String, Object> getMessage() {
		return message;
	}

	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ResponseMessage [message=" + message + ", success=" + success + "]";
	}
	
}
