package com.wincovid.service;

import java.time.LocalDate;
import java.util.List;

import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.Vaccine;
import com.wincovid.module.VaccineInventory;

public interface VaccineInventoryService {
	
	public VaccineInventory addVaccineCount(String key, int vaccineInventoryId,int vaccineCountID  )throws VaccineInventoryException,LoginException;
	
	public VaccineInventory updateVaccinelnventory(String key,int vaccineInventoryId,LocalDate date) throws LoginException, VaccineInventoryException;
	
	public VaccineInventory deleteVaccineInventory(String key,int vaccineInventoryid) throws LoginException, VaccineInventoryException;
	
	public List<VaccineInventory>  getVaccineInventoryByCenter(int vaccinationCentercode)throws VaccineInventoryException,VaccinationCenterException;
	
	public List<VaccineInventory> getVaccineInventoryByDate (LocalDate date) throws VaccineInventoryException ;

	public List<VaccineInventory>  getVaccinelnventoryByVaccineName(String vaccineName) throws VaccineInventoryException ;
}
