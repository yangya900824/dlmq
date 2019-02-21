package com.yangya.config;

public class MqConstant {

	/**
	 * 工作队列
	 */
	public static String HELLO_QUEUE_NAME = "HELLO_QUEUE_NAME";
	
	/**
	 * 重试队列（缓冲队列）
	 */
	public static String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "DEFAULT_REPEAT_TRADE_QUEUE_NAME";
	/**
	 * 死信队列
	 */
	public static String DEFAULT_DEAD_LETTER_QUEUE_NAME = "DEFAULT_DEAD_LETTER_QUEUE_NAME";
	/**
	 * 交换机
	 */
	public static String DEFAULT_EXCHANGE = "DEFAULT_EXCHANGE";

}
