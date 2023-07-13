package com.wincovid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.exception.VaccineException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.VaccinationCenter;
import com.wincovid.module.Vaccine;
import com.wincovid.module.VaccineInventory;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.VaccinationCenterRepo;

@Service
public class VaccinationCenterServicesImpl implements VaccinationCenterServices{
	@Autowired
	private VaccinationCenterRepo vaccinationCenterRepo;

	 @Autowired
	 private CurrentUserSessionRepo currentUserSessionRepo;

	@Override
	public VaccinationCenter addVaccineCenter(String key, VaccinationCenter vaccinationCenter)throws VaccinationCenterException, LoginException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==true) {


			Optional<VaccinationCenter> vv = vaccinationCenterRepo.findById(vaccinationCenter.getVaccinationCentercode());
			if(vv.isPresent()) {
				throw new VaccinationCenterException("Vaccination center already present ");
			}else {
				return vaccinationCenterRepo.save(vaccinationCenter); 
			}


		}else{
			throw new LoginException("Person logged in is not admin");
		}
		}else {
			throw new LoginException("To add Vaccination center login first ");
		}
	}

	@Override
	public VaccinationCenter updateVaccineCenter(String key, int vaccinationCentercode, VaccineInventory vaccineInventory)throws VaccinationCenterException,VaccineInventoryException,LoginException{
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==true) {


			Optional<VaccinationCenter> vv = vaccinationCenterRepo.findById(vaccinationCentercode);
			if(vv.isEmpty()) {
				throw new VaccinationCenterException("Vaccination center Not found  ");
			}else {
				VaccinationCenter toupdate = vv.get();
				toupdate.getVaccineInventory().add(vaccineInventory);
				vaccinationCenterRepo.save(toupdate);
				return toupdate;
			}

		}else{
			throw new LoginException("Person logged in is not admin");
		}
		}else {
			throw new LoginException("To update Vaccination center login first ");
		}
	}

	@Override
	public VaccinationCenter deleteVaccineCenter(String key, int vaccinationCentercode)
			throws VaccinationCenterException, LoginException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==true) {


			Optional<VaccinationCenter> vv = vaccinationCenterRepo.findById(vaccinationCentercode);
			if(vv.isEmpty()) {
				throw new VaccinationCenterException("Vaccination center Not found  ");
			}else {
				VaccinationCenter todelete = vv.get();
				vaccinationCenterRepo.delete(todelete);
				return todelete;
			}

		}else{
			throw new LoginException("Person logged in is not admin");
		}
		}else {
			throw new LoginException("To delete Vaccination center login first ");
		}
	}

	@Override
	public VaccinationCenter getVaccineCenter(int vaccinationCentercode) throws VaccinationCenterException {
		Optional<VaccinationCenter> vv = vaccinationCenterRepo.findById(vaccinationCentercode);
		if(vv.isEmpty()) {
			throw new VaccinationCenterException("Vaccination center Not found  ");
		}else {
			VaccinationCenter toget = vv.get();
			return toget;
		}
	}

}
