package com.naveejr.kafkaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikiMediaChangesConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesConsumer.class);

	@KafkaListener(topics = "wikimedia_recent_change", groupId = "myGroup")
	public void consume(String message) {
		LOGGER.info("Message received -> {}", message);
	}

}
