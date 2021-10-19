/**
 * @author: Guadalupe Medina
 * @version: 2021
*/

package com.stacktrace.Stacktrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class StacktraceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StacktraceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Stacktrace Crypto"));
	}

}
