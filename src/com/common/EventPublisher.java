package com.common;

import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.event.source.Event;
import com.event.source.EventMetadata;

import reactor.core.publisher.Mono;

public class EventPublisher {

	     private  KafkaProducer<String, Event> producer;
	     private final String processId;
	     private final int apiVersion;
	    
	     

	    public EventPublisher(KafkaProducer<String, Event> producer, String processId, int apiVersion) {
	        this.producer = producer;
	        this.processId = processId;
	        this.apiVersion = apiVersion;
	    }
	 
	public Mono<Void> fire(Event event){
		return Mono.create(sink -> {
			fillOut(event);
			String topic = Topics.eventTopicName(event.getClass());
			ProducerRecord<String, Event> record = new ProducerRecord<>(topic, 0, event.getMetadata().getTimestamp(),
                    event.getAggId(), event);
			
			producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    sink.success();
                  System.out.println(" New Event created.........");
                } else {
                    sink.error(exception);
                }
            });
			
		});
		
	}
	
	
	public void fillOut(Event event) {
        EventMetadata md = event.getMetadata();
        md.setEventId(generateId());
        md.setProcessId(processId);
        md.setVersion(apiVersion);

        if (md.getTimestamp() == 0) {
            md.setTimestamp(System.currentTimeMillis());
        }
    }
	
	 private String generateId() {
	        return UUID.randomUUID().toString();
	    }
}
