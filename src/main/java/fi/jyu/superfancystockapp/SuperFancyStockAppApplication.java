package fi.jyu.superfancystockapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuperFancyStockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperFancyStockAppApplication.class, args);
	}

}
