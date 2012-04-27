package com.github.rabid_fish;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

public class Initiator {
	
	@Produce(uri = "direct:start")
	protected ProducerTemplate producer;

	public void sendMessage() {
		
		producer.sendBody("add,2.00,1.50");
		System.out.println("Successfully sent message");
	}
}
