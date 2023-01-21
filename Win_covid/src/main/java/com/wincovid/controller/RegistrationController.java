package com.wincovid.controller;


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

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.dto.VaccineRegistrationDto;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.IdCard;
import com.wincovid.module.Member;
import com.wincovid.module.VaccineRegistration;
import com.wincovid.service.VaccineRegistrationService;

@RestController
@RequestMapping("/registrationController")
public class RegistrationController {
	@Autowired
	private VaccineRegistrationService vaccineRegistrationService;
	
	@PostMapping("/registration")
	public ResponseEntity<VaccineRegistration> registerHandler(@Valid @RequestBody VaccineRegistration vaccineRegistration) throws VaccineRegistrationException{

		VaccineRegistration savedUser = vaccineRegistrationService.addVaccineRegistration(vaccineRegistration);

		return new ResponseEntity<VaccineRegistration>(savedUser, HttpStatus.OK);

	}
	
	@PutMapping("/registration")
	public ResponseEntity<VaccineRegistration> updateUsearHandler(@Valid @RequestBody VaccineRegistration vaccineRegistration, @RequestParam("key") String key) throws LoginException,VaccineRegistrationException{

		VaccineRegistration updatedUser = vaccineRegistrationService.updateVaccineRegistration(key, vaccineRegistration);

		return new ResponseEntity<VaccineRegistration>(updatedUser, HttpStatus.OK);

	}
	
	@DeleteMapping("/registration/{key}")
	public ResponseEntity<String> deleteregistrationHandler(@PathVariable("key") String key) throws LoginException, VaccineRegistrationException{

		String updatedUser = vaccineRegistrationService.deleteVaccineRegistration(key);

		return new ResponseEntity<String>(updatedUser, HttpStatus.OK);

	}
	
	@GetMapping("/registration")
	public ResponseEntity<VaccineRegistrationDto> getRegistrationByMobileNoIdHandler(@RequestParam("mobileno") String mobileno) throws VaccineRegistrationException{

		VaccineRegistrationDto  updatedUser = vaccineRegistrationService.getVaccineRegistrationByMobileNo(mobileno);

		return new ResponseEntity<VaccineRegistrationDto>(updatedUser, HttpStatus.OK);

	}
	
	@GetMapping("/registrations")
	public ResponseEntity<List<Member>> getAllMemberByMobileNoIdHandler(@RequestParam("mobileno") String mobileno) throws VaccineRegistrationException{

		List<Member> allmembers = vaccineRegistrationService.getAllMemberByMobileNo(mobileno);

		return new ResponseEntity<List<Member>>(allmembers, HttpStatus.OK);

	}
	
	@PostMapping("/usearLogin")
	public ResponseEntity<CurrentUserSession> loginUsearHandler(@Valid @RequestBody UsearDto usr) throws LoginException {

		CurrentUserSession res = vaccineRegistrationService.loginUsear(usr);

		return new ResponseEntity<CurrentUserSession>(res, HttpStatus.OK);

	}
    
	@PostMapping("/usearLogout/{key}")
	public ResponseEntity<String> logoutAdminHandler(@PathVariable("key") String key) throws LoginException {

		String res = vaccineRegistrationService.logoutUsear(key);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}
}
