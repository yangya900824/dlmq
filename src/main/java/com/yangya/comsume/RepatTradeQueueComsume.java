package com.yangya.comsume;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yangya.config.MqConstant;
import com.yangya.produce.Produce;

@Component
public class RepatTradeQueueComsume {
	private static final Logger LOGGER = LoggerFactory.getLogger(RepatTradeQueueComsume.class);
	@Autowired
	private Produce produce;
	
	@RabbitListener(queues="DEFAULT_REPEAT_TRADE_QUEUE_NAME")
	@RabbitHandler
	public void process(String content) {
		produce.send(MqConstant.HELLO_QUEUE_NAME, content);
		LOGGER.info("process time is {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}