package telran.cars.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.cars.domain.entities.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
