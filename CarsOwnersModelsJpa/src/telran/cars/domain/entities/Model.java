package telran.cars.domain.entities;

import javax.persistence.*;


@Table(name = "models")
@Entity
public class Model {
	@Id
	public String modelName;
	public int volume;
	public String company;
	
	public Model() {}

	public Model(String modelName, int volume, String company) {
		super();
		this.modelName = modelName;
		this.volume = volume;
		this.company = company;
	}
	
	
}
