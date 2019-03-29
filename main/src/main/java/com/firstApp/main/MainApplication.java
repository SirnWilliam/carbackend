package com.firstApp.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.firstApp.main.domain.Car;
import com.firstApp.main.domain.CarRepository;
import com.firstApp.main.domain.Owner;
import com.firstApp.main.domain.OwnerRepository;
import com.firstApp.main.domain.User;
import com.firstApp.main.domain.UserRepository;

@SpringBootApplication
public class MainApplication {
	private static final Logger logger = 
			LoggerFactory.getLogger(MainApplication.class);
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		logger.info("Hello Sirn William");
	}

	@Bean
	CommandLineRunner runner() {
		return args-> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
			Owner owner1 = new Owner("Sirn", "William");
			Owner owner2 = new Owner("Robina", "Alyadako");
			ownerRepository.save(owner1);
			ownerRepository.save(owner2);
			String userPassword = passwordEncoder.encode("sirn");
			Car car = new Car("Toyota", "Camry", "Black", 2016, 16000, owner1);
			carRepository.save(car);
			car = new Car("Toyota", "Corlla", "White", 2015, 13000, owner2);
			carRepository.save(car);
			userRepository.save(new User("sirn",userPassword, "SIRN"));
			System.out.println(userPassword);
			
		};
	}
}
