
package com.hills.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
//https://github.com/dilipsundarraj1/kafka-for-developers-using-spring-boot/
//import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

	/*
	 * @Value("${kafka-group}") private String group;
	 */

	@Value("${cluster}")
	private String cluster;

	@Bean public ProducerFactory<String,String> getProducer()
	{
		Map<String,Object>  config = new HashMap<>(); config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, cluster); 
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,  StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,  StringSerializer.class); 
		//config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,  false); //onfig.put(ProducerConfig.k r
		config.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
		return new  DefaultKafkaProducerFactory<String,String>(config); 
		
	}
	@Bean
	public KafkaTemplate getKafkatemaplate() {
		return new KafkaTemplate<>(getProducer());
	}
}
