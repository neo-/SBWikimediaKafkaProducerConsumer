package com.naveejr.kafkaproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	public static final String TOPIC_NAME = "wikimedia_recent_change";
	public static final String WIKI_MEDIA_STREAM_URL = "https://stream.wikimedia.org/v2/stream/recentchange";
	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(TOPIC_NAME)
				.build();
	}

}
