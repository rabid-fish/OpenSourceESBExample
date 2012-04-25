package com.github.rabid_fish;

import https.github_com.rabid_fish.math.MathOperationRequest;
import https.github_com.rabid_fish.math.MathOperationResponse;
import https.github_com.rabid_fish.math.ObjectFactory;

import java.math.BigDecimal;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Test;

public class EsbExampleWebserviceClientTest {

	@Test
	public void testClientRequest() {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(https.github_com.rabid_fish.MathOperationPortType.class);
		factory.setAddress("http://localhost:8080/cijug-webservice");
		
		ObjectFactory objectFactory = new ObjectFactory();
		MathOperationRequest request = objectFactory.createMathOperationRequest();
		request.setOperation("add");
		
		List<BigDecimal> arguments = request.getArgument();
		arguments.add(new BigDecimal("1.00"));
		arguments.add(new BigDecimal("2.50"));
		
		https.github_com.rabid_fish.MathOperationPortType client = factory.create(https.github_com.rabid_fish.MathOperationPortType.class);
		MathOperationResponse response = client.processMathOperation(request);
		
		Assert.assertTrue(response.getError().length() == 0);
		Assert.assertTrue(response.getValue().equals(new BigDecimal("3.500")));
	}
}
