package com.cydeo.bootstrap;

import com.cydeo.entity.Car;
import com.cydeo.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    CarRepository carRepository;

    public DataGenerator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Car c1 = new Car("Mercedes", "ML");
        Car c2 = new Car("BMW", "M5");
        Car c3 = new Car("Honda", "Civic");

        //save these objects to db. save method is provided by Spring
        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
    }
}
