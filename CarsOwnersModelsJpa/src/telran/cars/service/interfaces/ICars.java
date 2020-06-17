package telran.cars.service.interfaces;

import telran.cars.api.*;


public interface ICars {
	ReturnCode addCar(CarDto carDto);
	ReturnCode addModel(ModelDto modelDto);
	ReturnCode addOwner(OwnerDto ownerDto);
	CarDto getCar(long regNumber);
	ModelDto getModel(String modelName);
	OwnerDto getOwner(int ownerId);
	
}
