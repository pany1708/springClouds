package spring.clouds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableFeignClients
@SpringBootApplication
public class MicroClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroClientApplication.class, args);
	}
}
