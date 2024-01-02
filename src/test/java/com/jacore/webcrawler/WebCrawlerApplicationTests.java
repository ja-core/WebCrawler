package com.jacore.webcrawler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Running the application context starts a endless cycle of extracting URLs")
class WebCrawlerApplicationTests {

	@Test
	void contextLoads() {
	}

}
