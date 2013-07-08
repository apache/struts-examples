package org.apache.struts.edit.service;

/**
 * Provides information on car models using data hard-coded
 * in this class.
 * @author bphillips
 *
 */
public class CarModelsServiceHardCoded implements CarModelsService {
	

	@Override
	public String[] getCarModels() {
		
		String [] carModelsAvailable = {"Ford","Chrysler","Toyota","Nissan"};
		
		return carModelsAvailable ;
		
		
	}

}
