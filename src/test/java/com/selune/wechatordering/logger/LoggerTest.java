package com.selune.wechatordering.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

	@Test
	public void test() {

		String name = "name";
		String password = "password";

		log.debug("debug....");
		log.info("info....");
		log.info("name= {}, password= {}", name, password);
		log.error("error....");
	}
}
