package com.bank.app;

import javax.swing.text.html.parser.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.bank.app.entity.Users.Card;
import com.bank.app.repository.Users.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
//@EnableWebMvc
public class CcavApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(CcavApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(CardRepository repo) {
		return args->{
			repo.deleteAll();
			
			repo.save(new Card(1,"Goldcard"));
			repo.save(new Card(2,"Platinumcard"));
			repo.save(new Card(3,"Classiccard"));
			repo.save(new Card(4,"Mastercard"));
			
		};
	}

	@Bean 
	RestTemplate restTemplete() {
		return new RestTemplate();
	}
	

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
	

}
