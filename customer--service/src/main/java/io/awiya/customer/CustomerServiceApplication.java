package io.awiya.customer;

import io.awiya.customer.entities.Customer;
import io.awiya.customer.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);
			customerRepository.saveAll(List.of(
					Customer.builder().name("Mohammed").email("med@gmail.com").build(),
					Customer.builder().name("Jad").email("jad@gmail.com").build(),
					Customer.builder().name("Judia").email("judia@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
