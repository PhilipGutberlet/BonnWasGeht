package com.springboot.bonnwasgeht;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.lang.Object;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BonnwasgehtApplicationTests {

	@Test
	void convertTimeTest() {
		assertEquals(EventList.convertTime("20:00:00"), "20:00");
	}

	@Test
	void convertDateTest(){
		assertEquals(EventList.convertDate("2020-05-10"),"10. Mai");
	}
}
