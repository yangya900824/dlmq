package com.yangya.produce;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yangya.config.DLXMessage;
import com.yangya.config.MqConstant;

@Component
public class Produce {
	private static final Logger LOGGER = LoggerFactory.getLogger(Produce.class);
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send(String queueName, String message) {
		amqpTemplate.convertAndSend(MqConstant.DEFAULT_EXCHANGE, queueName, message);
		LOGGER.info("send message:" + message + "----" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	public void send(String queueName, String msg, long time) {
		LOGGER.info("startTime is {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//DLXMessage dlxMessage = new DLXMessage(queueName, msg, time);
		DLXMessage dlxMessage = JSONObject.parseObject(msg,DLXMessage.class);
		MessagePostProcessor processor = new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setExpiration(dlxMessage.getTime()+"");
				return message;
			}
		};
		//dlxMessage.setExchange(MqConstant.DEFAULT_EXCHANGE);
		msg = JSONObject.toJSONString(dlxMessage);
		amqpTemplate.convertAndSend(MqConstant.DEFAULT_EXCHANGE, MqConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, msg, processor);
		LOGGER.info("endTime is {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}