package com.github.rabid_fish;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitiatorTest extends CamelSpringTestSupport {

	@Produce(uri = "direct:start")
	protected ProducerTemplate producer;

	@EndpointInject(uri = "mock:end")
    protected MockEndpoint consumer;

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		
		String path = "classpath:/camel/camelInitiatorContext.xml";
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
	public void testInitiatorSendMessage() {
		
		Initiator initiator = applicationContext.getBean(Initiator.class);
		initiator.sendMessage();
	}
}
