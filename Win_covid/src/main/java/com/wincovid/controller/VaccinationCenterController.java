package com.wincovid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.VaccinationCenter;
import com.wincovid.module.VaccineInventory;
import com.wincovid.service.VaccinationCenterServices;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {
	
	@Autowired
    private VaccinationCenterServices vaccinationCenterServices;
	
	@PostMapping("/addVaccinationCenter")
    public ResponseEntity<VaccinationCenter> addVaccineCenterHandler(@Valid @RequestBody VaccinationCenter VaccinationCenter, @RequestParam("key") String key) throws VaccinationCenterException, LoginException{
		
		VaccinationCenter saved = vaccinationCenterServices.addVaccineCenter(key, VaccinationCenter);
        
		return new ResponseEntity<VaccinationCenter>(saved, HttpStatus.CREATED);
    }
	
	@PutMapping("/updateVaccinationCenter")
    public ResponseEntity<VaccinationCenter> updateVaccineCenterHandler(@Valid @RequestBody VaccineInventory vaccineInventory, @RequestParam("key") String key,@RequestParam("vaccinationCentercode") int vaccinationCentercode) throws VaccinationCenterException, VaccineInventoryException, LoginException {
		
		VaccinationCenter update = vaccinationCenterServices.updateVaccineCenter(key, vaccinationCentercode, vaccineInventory);
        
		return new ResponseEntity<VaccinationCenter>(update, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{vaccinationCentercode}")
	
    public ResponseEntity<VaccinationCenter> deleteVaccineCenterHandler(@PathVariable("vaccinationCentercode") int vaccinationCentercode, @RequestParam("key") String key) throws VaccinationCenterException, LoginException{
        
		VaccinationCenter delete = vaccinationCenterServices.deleteVaccineCenter(key, vaccinationCentercode);
        
		return new ResponseEntity<VaccinationCenter>(delete, HttpStatus.OK);
    }
	
	@GetMapping("/VaccineCen")
   	public ResponseEntity<VaccinationCenter> getVaccineCenterHandler(@RequestParam("vaccinationCentercode") int vaccinationCentercode) throws VaccinationCenterException{

	   VaccinationCenter vaccinationCenter = vaccinationCenterServices.getVaccineCenter(vaccinationCentercode);

   	   return new ResponseEntity<VaccinationCenter>(vaccinationCenter, HttpStatus.OK);
   	}
}
