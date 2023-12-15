package dev.surya.labs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dev.surya.labs")
public class InterviewApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started.");
	}


	
}
