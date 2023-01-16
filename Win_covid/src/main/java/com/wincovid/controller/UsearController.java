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

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.UsearException;
import com.wincovid.module.Usear;
import com.wincovid.service.UsearServices;

@RestController
@RequestMapping("/usearController")
public class UsearController {
	@Autowired
	private UsearServices usearServices;
	
	@GetMapping("/usears/{key}")
	public ResponseEntity<Usear> getUserDetailsHandler(@PathVariable("key") String key) throws UsearException, LoginException {

		Usear existingUser = usearServices.findUsearBykey(key);

		return new ResponseEntity<Usear>(existingUser, HttpStatus.OK);

	}
	
	@PostMapping("/usearsignup")
	public ResponseEntity<Usear> registerUsearHandler(@Valid @RequestBody Usear user) throws UsearException {

		Usear savedUser = usearServices.registerUsear(user);

		return new ResponseEntity<Usear>(savedUser, HttpStatus.OK);

	}
	
	@PutMapping("/usears")
	public ResponseEntity<Usear> updateUsearHandler(@Valid @RequestBody Usear user, @RequestParam("key") String key)
			throws UsearException, LoginException {

		Usear updatedUser = usearServices.updateUser(user, key);

		return new ResponseEntity<Usear>(updatedUser, HttpStatus.OK);

	}
    
	@DeleteMapping("/usears/{key}")
	public ResponseEntity<String> deleteUsearHandler(@PathVariable("key") String key)
			throws UsearException, LoginException {

		String updatedUser = usearServices.deleteUsearAccount(key);

		return new ResponseEntity<String>(updatedUser, HttpStatus.OK);

	}
	
	@GetMapping("/usears")
	public ResponseEntity<Usear> getUsearByIdHandler(@RequestParam("uid") int uid)
			throws UsearException, LoginException {

		Usear updatedUser = usearServices.findUsearById(uid);

		return new ResponseEntity<Usear>(updatedUser, HttpStatus.OK);

	}
	
	@PostMapping("/usearLogin")
	public ResponseEntity<CurrentUserSession> loginUsearHandler(@Valid @RequestBody UsearDto usr) throws LoginException {

		CurrentUserSession res = usearServices.loginUsear(usr);

		return new ResponseEntity<CurrentUserSession>(res, HttpStatus.OK);

	}
    
	@PostMapping("/usearLogout/{key}")
	public ResponseEntity<String> logoutAdminHandler(@PathVariable("key") String key) throws LoginException {

		String res = usearServices.logoutUsear(key);

		return new ResponseEntity<String>(res, HttpStatus.OK);

	}
	
	@GetMapping("/usearsByAdhar")
	public ResponseEntity<Usear> getUsearByAdharHandler(@RequestParam("adharNo") String adharNo)
			throws UsearException, AdharCardException {

		Usear getUser = usearServices.findUsearAdharNo(adharNo);

		return new ResponseEntity<Usear>(getUser, HttpStatus.OK);

	}
	
	@GetMapping("/usearsByPan")
	public ResponseEntity<Usear> getUsearByPanHandler(@RequestParam("panNo") String panNo)
			throws PanCardException {

		Usear getUser = usearServices.findUsearpanNo(panNo);

		return new ResponseEntity<Usear>(getUser, HttpStatus.OK);

	}
}
