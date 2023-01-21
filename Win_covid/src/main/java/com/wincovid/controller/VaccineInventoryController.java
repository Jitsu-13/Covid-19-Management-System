package com.wincovid.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.wincovid.exception.VaccineException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.Vaccine;
import com.wincovid.module.VaccineInventory;
import com.wincovid.service.VaccineInventoryService;

@RestController
@RequestMapping("/inventory")
public class VaccineInventoryController {
	@Autowired
	private VaccineInventoryService vaccInvService;
	
	@PostMapping("/addVaccineCount")
    public ResponseEntity<VaccineInventory> addVaccineCountHandler(@RequestParam("vaccineInventoryId")  int vaccineInventoryId, @RequestParam("key") String key, @RequestParam("VaccineCountid") int VaccineCountid) throws VaccineInventoryException, LoginException {
		VaccineInventory saved = vaccInvService.addVaccineCount(key, vaccineInventoryId, VaccineCountid);
        return new ResponseEntity<VaccineInventory>(saved, HttpStatus.CREATED);
    }
	
	@PutMapping("/updateVaccineInventory")
    public ResponseEntity<VaccineInventory> updateVaccineInventoryHandler(@RequestParam("vaccineInventoryId") int vaccineInventoryId, @RequestParam("key") String key, @RequestParam("date") String date) throws VaccineException,LoginException, VaccineInventoryException{
		VaccineInventory getCus = vaccInvService.updateVaccinelnventory(key, vaccineInventoryId, LocalDate.parse(date));
        return new ResponseEntity<VaccineInventory>(getCus, HttpStatus.OK);
    }
	
	@DeleteMapping("/deleteVaccineInventory")
    public ResponseEntity<VaccineInventory> deleteVaccineInventoryHandler(@RequestParam("vaccineInventoryId") int vaccineInventoryId, @RequestParam("key") String key) throws LoginException, VaccineInventoryException {
		VaccineInventory getCus = vaccInvService.deleteVaccineInventory(key, vaccineInventoryId);
        return new ResponseEntity<VaccineInventory>(getCus, HttpStatus.OK);
    }
	
    @GetMapping("/allVaccineInventoryByDate")
    public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByDateHandler(@RequestParam("date") String date) throws VaccineInventoryException {
    	List<VaccineInventory> getapp = vaccInvService.getVaccineInventoryByDate(LocalDate.parse(date));
        return new ResponseEntity<List<VaccineInventory>>(getapp, HttpStatus.OK);
    }
    
    @GetMapping("/allVaccineInventoryByVaccineName")
    public ResponseEntity<List<VaccineInventory>> getVaccinelnventoryByVaccineNameHandler(@RequestParam("vaccineName") String vaccineName) throws VaccineInventoryException {
    	List<VaccineInventory> getapp = vaccInvService.getVaccinelnventoryByVaccineName(vaccineName);
        return new ResponseEntity<List<VaccineInventory>>(getapp, HttpStatus.FOUND);
    }
    
    @GetMapping("/allVaccineInventoryByCenter")
    public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByCenterHandler(@RequestParam("vaccinationCentercode") int vaccinationCentercode) throws VaccineInventoryException, VaccinationCenterException{
    	List<VaccineInventory> getapp = vaccInvService.getVaccineInventoryByCenter(vaccinationCentercode);
        return new ResponseEntity<List<VaccineInventory>>(getapp, HttpStatus.FOUND);
    }
	
//  public VaccineInventory getVaccineInventoryByCenter(int centerId)throws VaccineInventoryException;

}
