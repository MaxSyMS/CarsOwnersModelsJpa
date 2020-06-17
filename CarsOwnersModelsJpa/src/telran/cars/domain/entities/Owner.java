package telran.cars.domain.entities;

import java.util.List;

import javax.persistence.*;


@Table(name="owners")
@Entity
public class Owner {
	@Id
	public int id;
	public String name;
	public int birthYear;
	@OneToMany(mappedBy = "owner")
	List<Car> cars;
	
	public Owner() {}

	public Owner(int id, String name, int birthYear) {
		super();
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
	}

	public List<Car> getCars() {
		return cars;
	}

	
}
