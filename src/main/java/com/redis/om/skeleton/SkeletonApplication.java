package com.redis.om.skeleton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.redis.om.skeleton.models.CreditCard;
import com.redis.om.skeleton.models.Customer;
import com.redis.om.skeleton.models.repositories.CustomerRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;

import lombok.extern.slf4j.Slf4j;

@EnableRedisDocumentRepositories(basePackages = "com.redis.om.skeleton.*")
@SpringBootApplication
@Slf4j
public class SkeletonApplication {

	@Autowired
  	CustomerRepository customer_repo;

	public static void main(String[] args) {
		SpringApplication.run(SkeletonApplication.class, args);
	}
/* 
	@Bean
	CommandLineRunner loadTestData() {
	  	return args -> {
      		customer_repo.deleteAll();

			int n_records = 100000;

			Random rand = new Random(); 
      		Faker faker = new Faker();
	  		System.out.printf("Generating %d records....\n", n_records);
      		for (int i = 0; i < n_records; i++) {            
				String provider = faker.business().creditCardType();
				String number = faker.business().creditCardNumber();
				Date expire = new SimpleDateFormat("yyyy-MM-dd").parse(faker.business().creditCardExpiry());
				int security_code = rand.nextInt(999); 
				CreditCard credit_card = CreditCard.of(provider, number, expire, security_code);

				String name = faker.name().fullName();
				String address = faker.address().fullAddress();
				String phone = faker.phoneNumber().cellPhone();
				String job = faker.job().title();
				Customer customer = Customer.of(name, address, phone, job, credit_card);
		
				customer_repo.save(customer);
      		}
	  		System.out.printf("%d record generted!\n", n_records);
      
	  	};
	} 
*/
	void doTheQuery(String name) {
		log.info(StringUtils.repeat("=", 50));
		try {
			log.info("Searching for: '{}'", name);
			Iterable<Customer> customers = customer_repo.findByName(name);

			int n_found =  IterableUtils.size(customers);
			if (0 == n_found) {
				log.warn("\tThe query didn't return any records!!");

			} else {
				log.info("Found {} customers", n_found);

				for (Customer c: customers) {
					log.info("\t{} | {}", c.getName(), c.getJob());
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}		
		log.info(StringUtils.repeat("=", 50));
	}



	@Bean
	public CommandLineRunner doTheSearching(ApplicationContext ctx) {
		return args -> {
			String name = "Shirl*";
			doTheQuery(name);

			name = "Shirlene Breitenberg";
			doTheQuery(name);

			name = "Shirlene*";
			doTheQuery(name);

			name = "Shirlene B*";
			doTheQuery(name);

			name = "Shirl* B*";
			doTheQuery(name);

			name = "Shirl*\\ B*";
			doTheQuery(name);
		};
	}
}
