package fyi.shycat.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShycatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShycatApplication.class, args);
	}

}
