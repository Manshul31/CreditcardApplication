package com.mifinity.CreditCardApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The CreditCardApplication is the main class through which the entire application can be executed 
 * 
 * @author Manshul Khattar
 * @version 1.0
 */

@SpringBootApplication
public class CreditCardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CreditCardApplication.class);
    }

}
