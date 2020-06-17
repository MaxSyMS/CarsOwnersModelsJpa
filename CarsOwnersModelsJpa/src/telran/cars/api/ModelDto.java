package telran.cars.api;

public class ModelDto {
	public String modelName;
	public int volume;
	public String company;
	
	public ModelDto() {}

	public ModelDto(String modelName, int volume, String company) {
		super();
		this.modelName = modelName;
		this.volume = volume;
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + volume;
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
		ModelDto other = (ModelDto) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}
	
	

}
