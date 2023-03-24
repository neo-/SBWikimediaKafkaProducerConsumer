package com.naveejr.kafkaproducer.service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikiMediaChangesHandler implements EventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesHandler.class);
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topic;

	public WikiMediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}

	@Override
	public void onOpen() throws Exception {

	}

	@Override
	public void onClosed() throws Exception {

	}

	@Override
	public void onMessage(String s, MessageEvent messageEvent) {
		LOGGER.info("event data -> {}", messageEvent.getData());
		kafkaTemplate.send(topic, messageEvent.getData());
	}

	@Override
	public void onComment(String s) throws Exception {

	}

	@Override
	public void onError(Throwable throwable) {

	}
}
