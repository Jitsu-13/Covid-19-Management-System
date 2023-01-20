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
	
	@PostMapping("/addinventory")
    public ResponseEntity<VaccineInventory> addVaccineInventory(@RequestBody VaccineInventory vaccineInventory, @RequestParam("key") String key, @RequestParam("VaccineCountid") int VaccineCountid) throws VaccineInventoryException, LoginException {
		VaccineInventory saved = vaccInvService.addVaccineCount(key, vaccineInventory, VaccineCountid);
        return new ResponseEntity<VaccineInventory>(saved, HttpStatus.CREATED);
    }
	
	@PutMapping("/update")
    public ResponseEntity<VaccineInventory> updateVaccineInventory(@RequestBody VaccineInventory vaccineInventory, @RequestParam("key") String key, @RequestParam("PreviousStock") int PreviousStock) throws VaccineException,LoginException, VaccineInventoryException{
		VaccineInventory getCus = vaccInvService.updateVaccinelnventory(key, vaccineInventory, PreviousStock);
        return new ResponseEntity<VaccineInventory>(getCus, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete")
    public ResponseEntity<VaccineInventory> deleteAppointment(@RequestParam("vaccineInventoryId") int vaccineInventoryId, @RequestParam("key") String key) throws LoginException, VaccineInventoryException {
		VaccineInventory getCus = vaccInvService.deleteVaccineInventory(key, vaccineInventoryId);
        return new ResponseEntity<VaccineInventory>(getCus, HttpStatus.OK);
    }
	
    @GetMapping("/allVaccineInventoryByDate")
    public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByDate(@RequestParam("date") String date) throws VaccineInventoryException {
    	List<VaccineInventory> getapp = vaccInvService.getVaccineInventoryByDate(LocalDate.parse(date));
        return new ResponseEntity<List<VaccineInventory>>(getapp, HttpStatus.OK);
    }
    
    @GetMapping("/allVaccineInventory")
    public ResponseEntity<List<VaccineInventory>> getVaccinelnventoryByVaccineName(@RequestParam("vaccineName") String vaccineName) throws VaccineInventoryException {
    	List<VaccineInventory> getapp = vaccInvService.getVaccinelnventoryByVaccineName(vaccineName);
        return new ResponseEntity<List<VaccineInventory>>(getapp, HttpStatus.FOUND);
    }
	
//  public VaccineInventory getVaccineInventoryByCenter(int centerId)throws VaccineInventoryException;

}
