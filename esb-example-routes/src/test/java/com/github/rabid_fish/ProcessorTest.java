package com.github.rabid_fish;

import https.github_com.rabid_fish.math.MathOperationRequest;

import java.math.BigDecimal;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcessorTest extends CamelSpringTestSupport {

	@Produce(uri = "direct:start")
	protected ProducerTemplate producer;

	@EndpointInject(uri = "mock:end")
    protected MockEndpoint consumer;

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		
		String path = "classpath:/camel/camelProcessorContext.xml";
		System.getProperties().setProperty("spring.profiles.active", "test");
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
		return applicationContext;
	}

	@Test
	public void testRouteWithPingMessage() throws InterruptedException {
		
		consumer.expectedBodiesReceived("ping");
		producer.sendBody("ping");
		consumer.assertIsSatisfied();
		
		System.out.println("Successfully sent message");
	}
	
	@Test
	public void testProcessorCreateWebServiceRequest() {
		
		Processor processor = applicationContext.getBean(Processor.class);
		Exchange exchange = createExchangeWithBody("add,1.500,2.500");
		MathOperationRequest request = processor.createWebServiceRequest(exchange);
		
		assertTrue(request.getOperation().equals("add"));
		assertTrue(request.getArgument().get(0).equals(new BigDecimal("1.500")));
		assertTrue(request.getArgument().get(1).equals(new BigDecimal("2.500")));
	}
}
