package telran.cars.api;

public class OwnerDto {
	public int id;
	public String name;
	public int  birthYear;
	
	public OwnerDto() {}

	public OwnerDto(int id, String name, int birthYear) {
		super();
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		OwnerDto other = (OwnerDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
