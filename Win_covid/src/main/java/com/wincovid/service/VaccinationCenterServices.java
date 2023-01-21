package com.wincovid.service;

import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.VaccinationCenter;
import com.wincovid.module.VaccineCount;
import com.wincovid.module.VaccineInventory;

public interface VaccinationCenterServices {
	
	public VaccinationCenter addVaccineCenter(String key, VaccinationCenter vaccinationCenter)throws VaccinationCenterException,LoginException;
	
	public VaccinationCenter updateVaccineCenter(String key, int vaccinationCentercode, VaccineInventory vaccineInventory)throws VaccinationCenterException,VaccineInventoryException,LoginException;
	
	public VaccinationCenter deleteVaccineCenter(String key, int vaccinationCentercode)throws VaccinationCenterException,LoginException;
	
	public VaccinationCenter getVaccineCenter(int vaccinationCentercode)throws VaccinationCenterException;

}
