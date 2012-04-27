package com.github.rabid_fish;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExampleMain {

	public static void main(String[] args) throws Exception {
		
		if (args.length < 1) {
			throw new Exception("Missing spring context argument");
		}
		
        System.getProperties().setProperty("spring.profiles.active", "live");
        
        String applicationContextName = args[0];
        String applicationContextPath = getApplicationContextPath(applicationContextName);
		
		if (applicationContextName.equals("Initiator")) {
			runInitator(applicationContextPath);
		} else {
			runCamelMain(applicationContextPath);
		}
	}

	public static String getApplicationContextPath(String applicationContextName) throws Exception {
		
		String applicationContextPath = null;
        
		if (applicationContextName.equals("Initiator")) {
			applicationContextPath = "classpath:/camel/camelInitiatorContext.xml";
        } else if (applicationContextName.equals("Processor")) {
        	applicationContextPath = "classpath:/camel/camelProcessorContext.xml";
        } else if (applicationContextName.equals("Subscriber")) {
        	applicationContextPath = "classpath:/camel/camelSubscriberContext.xml";
        } else {
        	throw new Exception("First argument passed in to main must be one of [Initiator,Processor,Subscriber]");
        }
		return applicationContextPath;
	}
	
	public static void runInitator(String path) throws Exception {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
		CamelContext camelContext = applicationContext.getBean(CamelContext.class);
		camelContext.start();
		
		Initiator initiator = applicationContext.getBean(Initiator.class);
		initiator.sendMessage();
		
		Thread.sleep(2000);
		
		camelContext.stop();
		System.out.println("Stopping Camel, Initiator request sent");
	}
	
	public static void runCamelMain(String path) throws Exception {
		
        // http://camel.apache.org/running-camel-standalone-and-have-it-keep-running.html
		Main main = new Main();
        main.enableHangupSupport();
    	main.setApplicationContextUri(path);
        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        main.run();
		System.out.println("Stopping Camel");
	}
}
