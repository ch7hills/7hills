package com.hills.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	@Value("${topic}")
	private String topic;
	
	@Autowired
	KafkaTemplate<String,String> template;
	
	@GetMapping("/send")
	public String sendData() {
		Map<String,String> input =new HashMap<>();
		for(int i=1;i<10;i++) {
			try {
			template.send(topic, "Number->"+i);
			System.out.println(i);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return "success";
	}
}
