package com.yangya.config;
/**
 * 消息实体类
 * @author yangya
 *
 */
public class DLXMessage {

	private String queueName;
	
	private String msg = "33333";
	
	private int count = 1;
	
	private long time = 1000;
	
	private String exchange;
	
	
	public String getQueueName() {
		return queueName;
	}


	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}


	public String getExchange() {
		return exchange;
	}


	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public DLXMessage() {
	}

	public DLXMessage(String queueName, String msg, long time) {
		this.queueName = queueName;
		this.msg=msg;
		this.time = time;
	}


}
