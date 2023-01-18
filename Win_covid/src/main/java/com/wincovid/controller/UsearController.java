package com.wincovid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.dto.IdCardDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.module.IdCard;
import com.wincovid.service.IdCardService;

@RestController
@RequestMapping("/usearController")
public class UsearController {
	@Autowired
	private IdCardService usearServices;
	
	
	
	@PostMapping("/usearsignup")
	public ResponseEntity<IdCard> registerUsearHandler(@Valid @RequestBody IdCard user) throws IdCardException {

		IdCard savedUser = usearServices.addIdCard(user);

		return new ResponseEntity<IdCard>(savedUser, HttpStatus.OK);

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
	public ResponseEntity<IdCardDto> getUsearByAdharHandler(@RequestParam("adharNo") String adharNo)
			throws IdCardException, AdharCardException {

		IdCardDto getUser = usearServices.findIdCardByAdharNo(adharNo);

		return new ResponseEntity<IdCardDto>(getUser, HttpStatus.OK);

	}
	
	@GetMapping("/usearsByPan")
	public ResponseEntity<IdCardDto> getUsearByPanHandler(@RequestParam("panNo") String panNo)
			throws PanCardException, IdCardException {

		IdCardDto getUser = usearServices.findIdCardBypanNo(panNo);

		return new ResponseEntity<IdCardDto>(getUser, HttpStatus.OK);

	}
}
