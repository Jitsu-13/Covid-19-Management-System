package com.wincovid.service;

import java.time.LocalDate;
import java.util.List;

import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.Vaccine;
import com.wincovid.module.VaccineInventory;

public interface VaccineInventoryService {
	
	public VaccineInventory addVaccineCount  (String key, VaccineInventory  inv,int vaccineCountID  )throws VaccineInventoryException,LoginException;
	
	public VaccineInventory updateVaccinelnventory  (String key, VaccineInventory inv,int PreviousStock) throws LoginException, VaccineInventoryException;
	
	public VaccineInventory deleteVaccineInventory  (String key,int vaccineInventoryid) throws LoginException, VaccineInventoryException;
	
	public VaccineInventory getVaccineInventoryByCenter(int centerId)throws VaccineInventoryException;
	
	public List<VaccineInventory> getVaccineInventoryByDate (LocalDate date) throws VaccineInventoryException ;

	public List<VaccineInventory>  getVaccinelnventoryByVaccineName (String vaccineName) throws VaccineInventoryException ;
}
