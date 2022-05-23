package org.sid.ecomapp;

import org.sid.ecomapp.entities.Category;
import org.sid.ecomapp.entities.Product;
import org.sid.ecomapp.repositories.CategoryRepository;
import org.sid.ecomapp.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EcomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAppApplication.class, args);
	}
	// si on a pas mis la representation bean cette methode ne sera jalais exuter
	/*@Bean
	public CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository){
		return args -> {
			Stream.of("Computer","Printer","SmartPhone").forEach(
				name->{
					productRepository.save(new Product(UUID.randomUUID().toString(),name,Math.random()*8000,Math.random()*100));
				}
			);
		};
	}*/
	@Bean
	public CommandLineRunner start(ProductRepository productRepository, CategoryRepository categoryRepository){
		return args -> {
			Stream.of("Computer","Printer","SmartPhone").forEach(
					name->{
						Category c = new Category();
						c.setName(name);
						categoryRepository.save(c);
					}
			);
			categoryRepository.findAll().forEach(cat->{
				for (int i = 1;i<=5;i++){
					Product product =  new Product();
					product.setId(UUID.randomUUID().toString());
					product.setPrice(Math.random()*9000);
					product.setQuantity(1+Math.random()*50);
					product.setName(cat.getName()+"-"+i);
					product.setCategory(cat);
					productRepository.save(product);

				}
			});
		};

	}
}
