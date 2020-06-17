package telran.cars.domain.entities;

import java.time.LocalDate;

import javax.persistence.*;

@Table(name="cars")
@Entity
public class Car {
	@Id
	public long regNumber;
	public String color;
	public LocalDate purchaseDate;
	@ManyToOne
	Owner owner;
	@ManyToOne
	Model model;

	public Car() {}

	public Car(long regNumber, String color, LocalDate purchaseDate, Owner owner, Model model) {
		super();
		this.regNumber = regNumber;
		this.color = color;
		this.purchaseDate = purchaseDate;
		this.owner = owner;
		this.model = model;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	
	
	
}
