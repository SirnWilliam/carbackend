package com.firstApp.main;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstApp.main.domain.Car;
import com.firstApp.main.domain.CarRepository;
import com.firstApp.main.domain.Owner;
import com.firstApp.main.domain.OwnerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private CarRepository carRepository;
	@Autowired 
	private OwnerRepository ownerRepository;
	
	@Test
	public void saveCars() {
		
		Owner owner = new Owner("Sirn", "William");
		ownerRepository.save(owner);
		Car car = new Car("Toyota", "Camry", "Black", 2016, 16000, owner);
		entityManager.persistAndFlush(car);
		assertThat(car.getId()).isNotNull();
	}
	
	@Test
	public void deleteCars() {
		Owner owner = new Owner("Sirn", "William");
		ownerRepository.save(owner);
		entityManager.persistAndFlush(new Car("Toyota", "Camry", "Black", 2016, 16000, owner));
		entityManager.persistAndFlush(new Car("Mini", "Cooper", "Yellow",2015, 24500, owner));
		carRepository.deleteAll();
		assertThat(carRepository.findAll()).isEmpty();
	}
}
