package com.github.rabid_fish;

import org.apache.camel.Exchange;

public class Subscriber {

	public void logResponse(Exchange exchange) {
		
		String response = exchange.getIn().getBody(String.class);
		System.out.println(response);
	}

}
