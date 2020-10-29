package com.hills.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer3 {
	
	@Value("${topic}")
	private String topic;
	
	@Value("${kafka-group-two}")
	private String groupTwo;
	
	/*
	 * @KafkaListener(topics="${topic}",groupId ="${kafka-group-two}",
	 * containerFactory = "ConcurrentKafkaListenerContainerFactory") public void
	 * consume(String message) { System.out.println("Cosumer 3:"+message); }
	 */
	 @KafkaListener(topics="${topic}",groupId ="${kafka-group-two}", containerFactory = "GroupTwoConsumer") 
	 public void consume(String message) {
		 System.out.println("Cosumer 3:"+message); 
	 }
	/*
	@KafkaListener(topicPartitions = @TopicPartition(topic = "${topic}", partitions = { "3" }), groupId="${kafka-group-one}",containerFactory = "kafkaListenerContainerFactory")
	public void listenToPartition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		System.out.println("Cosumer 3: Received Message: " + message + " from partition: " + partition);
	}
	*/
}
