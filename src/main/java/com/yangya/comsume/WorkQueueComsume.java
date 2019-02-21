package com.yangya.comsume;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yangya.config.DLXMessage;
import com.yangya.produce.Produce;

@Component
public class WorkQueueComsume {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkQueueComsume.class);
	
	@Autowired
	private Produce produce;
		
	@RabbitListener(queues="HELLO_QUEUE_NAME")
	@RabbitHandler
	public void process(String message) {
	   LOGGER.info("消費hello_queue " + message + "---" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	   DLXMessage msg = JSONObject.parseObject(message, DLXMessage.class);
	   if(msg.getCount()==1){
		   msg.setTime(5000);
	   }else if(msg.getCount()==2){
		   msg.setTime(10000);
	   }else if(msg.getCount()==3){
		   msg.setTime(15000);
	   }else if(msg.getCount()==4){
		   msg.setTime(20000);
	   }else if(msg.getCount()==5){
		   msg.setTime(25000);
	   }
	   msg.setCount(msg.getCount()+1);
	   if(msg.getCount()<=5){
		   produce.send("DEFAULT_REPEAT_TRADE_QUEUE_NAME", JSONObject.toJSONString(msg), msg.getTime());
	   }
    }
}