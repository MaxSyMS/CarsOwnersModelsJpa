package telran.cars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.cars.api.CarDto;
import telran.cars.api.ModelDto;
import telran.cars.api.OwnerDto;
import telran.cars.api.ReturnCode;
import telran.cars.service.interfaces.ICars;


@SpringBootApplication
public class CarsOwnersModelsTests {
	private static final long REG_NUMBER1 = 1;
	private static final int YEAR1 = 2015;
	private static final String MODEL_NAME1 = "model1";
	private static final String COMPANY_NAME1 = "company1";
	private static final int VOLUME1 = 100;
	private static final int OWNER_ID1 = 1;
	private static final String NAME1 = "name1";
	private static final int BIRTH_YEAR1 = 1980;
	private static final long NEW_REG_NUMBER = 1111111;
	private static final String NEW_MODEL_NAME = "new model";
	private static final int NEW_OWNER_ID = 2222;
	
	
	ConfigurableApplicationContext ctx;
	
	CarDto carDto1 = new CarDto(REG_NUMBER1, "red", LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, OWNER_ID1);
	
	ModelDto modelDto1 = new ModelDto(MODEL_NAME1, VOLUME1, COMPANY_NAME1);
	
	OwnerDto ownerDto1 = new OwnerDto(OWNER_ID1, NAME1, BIRTH_YEAR1);
	
	ICars cars;
	

	@Before
	public void setUp() throws Exception {
		ctx = SpringApplication.run(CarsOwnersModelsTests.class);
		cars = ctx.getBean(ICars.class);
		cars.addModel(modelDto1);
		cars.addOwner(ownerDto1);
		cars.addCar(carDto1);
		
	}

	@After
	public void tearDown() throws Exception {
		ctx.close();
	}

	@Test
	public void testAddCar() {
		//**********************NULL CHECK************************
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addCar(null));
				
		CarDto nullCarDto1 = new CarDto(-1, "red", LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addCar(nullCarDto1));
				
		CarDto nullCarDto2 = new CarDto(1, null, LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addCar(nullCarDto2));
				
		CarDto nullCarDto3 = new CarDto(3, "red", null, MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.WRONG_PURCHASE_DATE, cars.addCar(nullCarDto3));
				
		CarDto nullCarDto4 = new CarDto(4, "red", LocalDate.ofYearDay(YEAR1, 1), null, OWNER_ID1);
		assertEquals(ReturnCode.NO_MODEL, cars.addCar(nullCarDto4));
				
		CarDto nullCarDto5 = new CarDto(5, "red", LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, 0);
		assertEquals(ReturnCode.NO_OWNER, cars.addCar(nullCarDto5));
		//**********************NULL CHECK************************
				
		assertEquals(ReturnCode.CAR_ALREADY_EXISTS, cars.addCar(carDto1));
				
		CarDto carDtoWrongDate1 = new CarDto(6, "red", LocalDate.now().plusDays(1), MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.WRONG_PURCHASE_DATE, cars.addCar(carDtoWrongDate1));
		
		CarDto carDtoWrongDate2 = new CarDto(7, "red", LocalDate.of(1886, 01, 28), MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.WRONG_PURCHASE_DATE, cars.addCar(carDtoWrongDate2));
		
		CarDto carNoModel = new CarDto(8, "red", LocalDate.ofYearDay(YEAR1, 1), NEW_MODEL_NAME, OWNER_ID1);
		assertEquals(ReturnCode.NO_MODEL, cars.addCar(carNoModel));
		
		CarDto carNoOwner = new CarDto(9, "red", LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, NEW_OWNER_ID);
		assertEquals(ReturnCode.NO_OWNER, cars.addCar(carNoOwner));
		
		CarDto newCarDto = new CarDto(NEW_REG_NUMBER, "red", LocalDate.ofYearDay(YEAR1, 1), MODEL_NAME1, OWNER_ID1);
		assertEquals(ReturnCode.OK, cars.addCar(newCarDto));
				
	}

	@Test
	public void testAddModel() {
		ModelDto newModel = new ModelDto(NEW_MODEL_NAME, VOLUME1, COMPANY_NAME1);
		assertEquals(ReturnCode.MODEL_ALREADY_EXISTS, cars.addModel(modelDto1));
		assertEquals(ReturnCode.OK, cars.addModel(newModel));
	}

	@Test
	public void testAddOwner() {
		//**********************NULL CHECK************************
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addOwner(null));
		OwnerDto nullOwnerDto1 = new OwnerDto(-1, NAME1, BIRTH_YEAR1);
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addOwner(nullOwnerDto1));
		OwnerDto nullOwnerDto2 = new OwnerDto(1, null, BIRTH_YEAR1);
		assertEquals(ReturnCode.WRONG_PARAMETRE, cars.addOwner(nullOwnerDto2));
		//**********************NULL CHECK************************
		
		assertEquals(ReturnCode.OWNER_ALREADY_EXISTS, cars.addOwner(ownerDto1));
		
		OwnerDto wrongYearOwnerDto1 = new OwnerDto(3, NAME1, LocalDate.now().minusYears(121).getYear());
		assertEquals(ReturnCode.WRONG_YEAR, cars.addOwner(wrongYearOwnerDto1));
		OwnerDto wrongYearOwnerDto2 = new OwnerDto(4, NAME1, 2021);
		assertEquals(ReturnCode.WRONG_YEAR, cars.addOwner(wrongYearOwnerDto2));
		
		OwnerDto newOwnerDto = new OwnerDto(NEW_OWNER_ID, NAME1, 2020);
		assertEquals(ReturnCode.OK, cars.addOwner(newOwnerDto));
		
	}

	@Test
	public void testGetCar() {
	assertNull(cars.getCar(-1));
	assertNull(cars.getCar(2));
	assertEquals(carDto1, cars.getCar(REG_NUMBER1));		
	}

	@Test
	public void testGetModel() {
	assertNull(cars.getModel(null));
	assertNull(cars.getModel("hello"));
	assertEquals(modelDto1, cars.getModel(MODEL_NAME1));
	
	}

	@Test
	public void testGetOwner() {
	assertNull(cars.getOwner(-1));
	assertNull(cars.getOwner(2));
	assertEquals(ownerDto1, cars.getOwner(OWNER_ID1));
	}

}
