package com.github.rabid_fish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/beans.xml" })
public class EsbExampleWebserviceSmokeTest {

	@Test
	public void testRunSmokeTest() {
		System.out.println("Success!");
	}
	
}
