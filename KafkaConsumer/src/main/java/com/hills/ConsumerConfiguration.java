package com.hills;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

@Configuration
public class ConsumerConfiguration {
	@Value("${kafka-group-one}")
	private String groupOne;
	
	@Value("${kafka-group-two}")
	private String groupTwo;

	@Value("${cluster}")
	private String cluster;
	
	@Bean
	public ConsumerFactory<String,Object> getConsumerForGroupOne(){
		Map<String,Object> map = new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cluster);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, groupOne);
		return new DefaultKafkaConsumerFactory<String,Object>(map);
	}
	
	@Bean(name="GroupOneConsumer")
	public ConcurrentKafkaListenerContainerFactory<String,Object> getGroupOneConsumerfactorty(){
		ConcurrentKafkaListenerContainerFactory<String,Object> groupOneConsumer = new ConcurrentKafkaListenerContainerFactory<String,Object>();
		groupOneConsumer.setConsumerFactory(getConsumerForGroupOne());
		return groupOneConsumer;
	}
	
	@Bean
	public ConsumerFactory<String,Object> getConsumerForGrouptwo(){
		Map<String,Object> map = new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cluster);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, groupTwo);
		return new DefaultKafkaConsumerFactory<String,Object>(map);
	}
	
	@Bean(name="GroupTwoConsumer")
	public ConcurrentKafkaListenerContainerFactory<String,Object> getGroupTwoConsumerfactorty(){
		ConcurrentKafkaListenerContainerFactory<String,Object> groupOneConsumer = new ConcurrentKafkaListenerContainerFactory<String,Object>();
		groupOneConsumer.setConsumerFactory(getConsumerForGrouptwo());
		return groupOneConsumer;
	}
	
}
