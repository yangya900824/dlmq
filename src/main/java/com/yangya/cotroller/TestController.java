package com.yangya.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yangya.config.DLXMessage;
import com.yangya.produce.Produce;

@RestController
public class TestController {

	@Autowired
	private Produce produce;
	
	@GetMapping("/send")
	public void send(){
		String queueName = "HELLO_QUEUE_NAME";
		String msg = "";
		DLXMessage message = new DLXMessage();
		msg = JSONObject.toJSONString(message);
		produce.send(queueName, msg);
	}
}
