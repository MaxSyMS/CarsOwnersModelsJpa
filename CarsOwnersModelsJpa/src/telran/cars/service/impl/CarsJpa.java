package telran.cars.service.impl;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.cars.api.CarDto;
import telran.cars.api.ModelDto;
import telran.cars.api.OwnerDto;
import telran.cars.api.ReturnCode;
import telran.cars.domain.entities.Car;
import telran.cars.domain.entities.Model;
import telran.cars.domain.entities.Owner;
import telran.cars.domain.repo.CarRepository;
import telran.cars.domain.repo.ModelRepository;
import telran.cars.domain.repo.OwnerRepository;
import telran.cars.service.interfaces.ICars;

@Service
public class CarsJpa implements ICars {
	
	@Autowired
	CarRepository carRepo;
	@Autowired
	ModelRepository modelRepo;
	@Autowired
	OwnerRepository ownerRepo;
	
	@Override
	@Transactional
	public ReturnCode addCar(CarDto carDto) {
		//null check
		if (carDto==null||carDto.regNumber<=-1
				||carDto.color==null) {
			return ReturnCode.WRONG_PARAMETRE;
		}
		//Car exist check
		if(carRepo.existsById(carDto.regNumber)) {
			return ReturnCode.CAR_ALREADY_EXISTS;
		}	
		//purchaseDate check
		if(carDto.purchaseDate==null||carDto.purchaseDate.isAfter(LocalDate.now())||carDto.purchaseDate
				.isBefore(LocalDate.of(1886, 01, 29))) {
			return ReturnCode.WRONG_PURCHASE_DATE;
		}
		//model check
		if (carDto.modelName==null||!modelRepo.existsById(carDto.modelName)) {
			return ReturnCode.NO_MODEL;
		}
		//owner check
		if (carDto.ownerId==0||!ownerRepo.existsById(carDto.ownerId)) {
			return ReturnCode.NO_OWNER;
		}
		
		Car car = new Car(carDto.regNumber,carDto.color, carDto.purchaseDate,
				ownerRepo.findById(carDto.ownerId).get(),
				modelRepo.findById(carDto.modelName).get());
		
		carRepo.save(car);
		
		return ReturnCode.OK;
	}
	
	@Override
	@Transactional
	public ReturnCode addModel(ModelDto modelDto) {
		if(modelDto==null) return ReturnCode.WRONG_PARAMETRE;
		if(modelRepo.existsById(modelDto.modelName))
			return ReturnCode.MODEL_ALREADY_EXISTS;
		Model model = new Model(modelDto.modelName, modelDto.volume, modelDto.company);
		modelRepo.save(model);
		return ReturnCode.OK;
	}
	@Override
	@Transactional
	public ReturnCode addOwner(OwnerDto ownerDto) {
		if (ownerDto==null||ownerDto.id<=-1||ownerDto.name==null) {
			return ReturnCode.WRONG_PARAMETRE;
		}
		if (ownerRepo.existsById(ownerDto.id)) {
			return ReturnCode.OWNER_ALREADY_EXISTS;
		}
		if (ownerDto.birthYear<LocalDate.now().minusYears(120).getYear()
				||ownerDto.birthYear>LocalDate.now().getYear()) {
			return ReturnCode.WRONG_YEAR;
		}
		
		Owner owner = new Owner(ownerDto.id, ownerDto.name, ownerDto.birthYear);
		ownerRepo.save(owner);
		return ReturnCode.OK;
	}
	@Override
	public CarDto getCar(long regNumber) {
		if (regNumber<=-1) {
			return null;
		}
		Car car = carRepo.findById(regNumber).orElse(null);
		if (car==null) {
			return null;
		}
		return new CarDto(car.regNumber, car.color, car.purchaseDate, 
				car.getModel().modelName,
				car.getOwner().id);
	}
	
	@Override
	public ModelDto getModel(String modelName) {
		if (modelName==null) {
			return null;
		}
		Model model = modelRepo.findById(modelName).orElse(null);
		if (model==null) {
			return null;
		}
		return new ModelDto(model.modelName,model.volume,model.company);
	}
	@Override
	public OwnerDto getOwner(int ownerId) {
		if (ownerId<=-1) {
			return null;
		}
		Owner owner = ownerRepo.findById(ownerId).orElse(null);
		if (owner==null) {
			return null;
		}
		return new OwnerDto(owner.id,owner.name,owner.birthYear);
	}
	
	

}
