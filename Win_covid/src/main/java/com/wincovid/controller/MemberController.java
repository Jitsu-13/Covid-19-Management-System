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

import com.wincovid.dto.MemberUpdateDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.MemberException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.Member;
import com.wincovid.service.MemberServices;

@RestController
@RequestMapping("/memberController")
public class MemberController {
	@Autowired
	private MemberServices memberServices;
	
	@GetMapping("/member/{key}/{idcardid}")
	public ResponseEntity<Member> getMemberByldHandler(@PathVariable("key") String key,@PathVariable("idcardid") int idcardid) throws LoginException,MemberException,IdCardException {

		Member existingMember = memberServices.getMemberByld(key, idcardid);

		return new ResponseEntity<Member>(existingMember, HttpStatus.OK);

	}
	
	@PostMapping("/addmember/{key}")
	public ResponseEntity<Member> addMemberHandler(@Valid @RequestBody Member member,@PathVariable("key") String key) throws LoginException, MemberException, VaccineRegistrationException{

		Member savedUser = memberServices.addMember(key, member);

		return new ResponseEntity<Member>(savedUser, HttpStatus.OK);

	}
	
	@PutMapping("/updatemember/{key}/{idcardid}")
	public ResponseEntity<Member> updateMemberHandler(@Valid @RequestBody  MemberUpdateDto MemberUpdateDto,@PathVariable("key") String key,@PathVariable("idcardid") int idcardid) throws LoginException, IdCardException, MemberException, VaccineRegistrationException
			{

		Member updatedUser = memberServices.updateMember(key,idcardid,MemberUpdateDto);

		return new ResponseEntity<Member>(updatedUser, HttpStatus.OK);

	}
    
	@DeleteMapping("/deletemember/{key}/{idcardid}")
	public ResponseEntity<Member> deleteUsearHandler(@PathVariable("key") String key,@PathVariable("idcardid") int idcardid) throws LoginException, MemberException, IdCardException, VaccineRegistrationException{

		Member updatedUser = memberServices.deleteMember(key, idcardid);

		return new ResponseEntity<Member>(updatedUser, HttpStatus.OK);

	}
	
	@GetMapping("/usearsByAdhar")
	public ResponseEntity<Member> getMemberByAdharNoHandler(@RequestParam("adharNo") String adharNo) throws MemberException, AdharCardException{

		Member getUser = memberServices.getMemberByAdharNo(adharNo);

		return new ResponseEntity<Member>(getUser, HttpStatus.OK);

	}
	
	@GetMapping("/usearsByPan")
	public ResponseEntity<Member> getUsearByPanHandler(@RequestParam("panNo") String panNo) throws MemberException, PanCardException{

		Member getUser = memberServices.getMemberByPanNo(panNo);

		return new ResponseEntity<Member>(getUser, HttpStatus.OK);

	}
}
