package com.naveejr.kafkaproducer.service;

import com.launchdarkly.eventsource.EventSource;
import com.naveejr.kafkaproducer.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class WikiMediaChangesProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage() {
		var changesHandler = new WikiMediaChangesHandler(kafkaTemplate, KafkaTopicConfig.TOPIC_NAME);
		try (var eventSource = new EventSource.Builder(changesHandler, new URI(KafkaTopicConfig.WIKI_MEDIA_STREAM_URL))
				.build()
		) {

			eventSource.start();
			TimeUnit.SECONDS.sleep(60);


		} catch (URISyntaxException e) {
			LOGGER.error("Invalid URI", e);
		} catch (InterruptedException e) {
			LOGGER.error("Thread interrupted", e);
		}

	}

}
