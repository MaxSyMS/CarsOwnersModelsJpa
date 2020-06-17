package telran.cars.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.domain.entities.Model;

public interface ModelRepository extends JpaRepository<Model, String> {

}
