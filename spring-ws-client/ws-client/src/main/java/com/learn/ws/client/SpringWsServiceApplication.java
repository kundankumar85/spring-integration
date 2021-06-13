package com.learn.ws.client;

import com.learn.ws.client.country.CountryClient;
import com.learn.ws.client.wsdl.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringWsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWsServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(CountryClient quoteClient) {
		return args -> {
			String country = "Spain";

			if (args.length > 0) {
				country = args[0];
			}
			GetCountryResponse response = quoteClient.getCountry(country);
			System.out.println("Country :"+country+" currenty is :"+response.getCountry().getCurrency());
		};
	}
}
