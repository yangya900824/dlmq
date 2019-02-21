package com.yangya.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	/**
	 * 定义工作队列
	 * @return
	 */
	@Bean
	public Queue hello() {
		return new Queue(MqConstant.HELLO_QUEUE_NAME);
	}
	/**
	 * 绑定公作队列到交换机
	 * @return
	 */
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(hello()).to(defaultChange()).with(MqConstant.HELLO_QUEUE_NAME);
	}
	/**
	 * 定义交换机
	 * @return
	 */
	@Bean
	public DirectExchange defaultChange() {
		return new DirectExchange(MqConstant.DEFAULT_EXCHANGE, true, false);
	}
	
	/**
	 * 定义缓冲队列
	 * @return
	 */
	@Bean
	public Queue repeatTradeQueue() {
		return new Queue(MqConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME, true, false, false);
	}
	
	/**
	 * 绑定缓冲队列到交换机
	 * @return
	 */
	@Bean
	public Binding drepeatTradeBinding() {
		return BindingBuilder.bind(repeatTradeQueue()).to(defaultChange()).with(MqConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
	}
	
	/**
	 * 定义死信队列，指定死信队列转发到的缓冲队列
	 * @return
	 */
	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> map = new HashMap<>();
		map.put("x-dead-letter-exchange", MqConstant.DEFAULT_EXCHANGE);
		map.put("x-dead-letter-routing-key", MqConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
		Queue queue = new Queue(MqConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, true, false, false, map);
		System.out.println("arguments :" + queue.getArguments());
		return queue;
	}
	
	/**
	 * 将死信队列绑定到交换机并且指定死信转发队列
	 * @return
	 */
	@Bean
	public Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(defaultChange()).with(MqConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
	}
}