package telran.cars.api;

import java.time.LocalDate;

public class CarDto {
	public long regNumber;
	public String color;
	public LocalDate purchaseDate;
	public String modelName;
	public int ownerId;
	
	public CarDto() {}

	public CarDto(long regNumber, String color, LocalDate purchaseDate, String modelName, int ownerId) {
		super();
		this.regNumber = regNumber;
		this.color = color;
		this.purchaseDate = purchaseDate;
		this.modelName = modelName;
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (regNumber ^ (regNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDto other = (CarDto) obj;
		if (regNumber != other.regNumber)
			return false;
		return true;
	}
	
	

}
