package telran.cars.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.domain.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
