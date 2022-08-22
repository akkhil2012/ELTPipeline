package com.common;

import com.event.source.Event;

public class Topics {
	
	public static final String TOPIC_NAME_PREFIX = "asset-";

	
	public static <E extends Event> String eventTopicName(Class<E> eventType) {
		
		return TOPIC_NAME_PREFIX+ "event" + "." + eventType.getSimpleName().replaceAll("(.)(\\p{Upper}+)", "$1-$2")
	            .toLowerCase();
	}

}
