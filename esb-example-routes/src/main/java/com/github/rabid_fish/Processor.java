package com.github.rabid_fish;

import https.github_com.rabid_fish.math.MathOperationRequest;
import https.github_com.rabid_fish.math.MathOperationResponse;
import https.github_com.rabid_fish.math.ObjectFactory;

import java.math.BigDecimal;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class Processor {

	public MathOperationRequest createWebServiceRequest(Exchange exchange) {
		
		Message message = exchange.getIn();
		String messageString = message.getBody(String.class); //"add,2.000,1.500";
		String[] values = messageString.split(",");
		
		ObjectFactory objectFactory = new ObjectFactory();
		MathOperationRequest request = objectFactory.createMathOperationRequest();
		request.setOperation(values[0]);
		
		List<BigDecimal> arguments = request.getArgument();
		for (int i = 1; i < values.length; i++) {
			arguments.add(new BigDecimal(values[i]));
		}
		
		System.out.println("Successfully created web service request");
		return request;
	}
	
	public String readValueFromResponse(Exchange exchange) {

		// Caused by: javax.xml.bind.UnmarshalException: unexpected element (uri:"https://github.com/rabid-fish/math", local:"MathOperationRequest"). Expected elements are <{https://github.com/rabid-fish/math}MathOperationResponse>
		
		MathOperationResponse response = exchange.getIn().getBody(MathOperationResponse.class);
		
		if (response.getError() != null && response.getError().length() > 0) {
			return response.getError();
		}
		
		String message = "The calculated value is: " + response.getValue().toString();
		System.out.println(message);
		return message;
	}
	
}
