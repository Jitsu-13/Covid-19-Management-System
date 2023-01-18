package com.wincovid.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.IdCardDto;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.PanCardException;
import com.wincovid.module.AdharCard;
import com.wincovid.module.IdCard;
import com.wincovid.module.PanCard;
import com.wincovid.repository.AdharCardRepo;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.IdCardRepo;
import com.wincovid.repository.PanCardRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class IdCardServiceImpl implements IdCardService{
    @Autowired
	private IdCardRepo idCardRepo;
    @Autowired
	private PanCardRepo panCardRepo;
	
	@Autowired
	private AdharCardRepo adharCardRepo;
	
	@Autowired
	private CurrentUserSessionRepo cuRepo;
	

	@Override
	public IdCard addIdCard(IdCard idCard) throws IdCardException {
		IdCard existingUserName = idCardRepo.findBymemberUserName(idCard.getMemberUserName());

		if (existingUserName != null) {
			throw new IdCardException("Usear already exist with this admin user name -  " + idCard.getMemberUserName());
		}else {
			return idCardRepo.save(idCard);
		}
	}

	@Override
	public IdCardDto findIdCardByAdharNo(String adharNo) throws IdCardException, AdharCardException {
		AdharCard padrcad = adharCardRepo.findByadharNo(adharNo);
		if (padrcad == null) {
			throw new AdharCardException("No user found with this aadhaar card number ");
		}else {
			IdCard existingUsear =  padrcad.getUsear();
			
			IdCardDto uddto = new IdCardDto();
			uddto.setName(existingUsear.getName());
			uddto.setDateOfBirth(existingUsear.getDateOfBirth());
			uddto.setGender(existingUsear.getGender());
			uddto.setAddress(existingUsear.getAddress());
			uddto.setCity(existingUsear.getCity());
			uddto.setState(existingUsear.getState());
			uddto.setPanCard(existingUsear.getPanCard());
			uddto.setAdharCard(existingUsear.getAdharCard());
			
			
			return uddto;
	}
	}

	@Override
	public IdCardDto findIdCardBypanNo(String panNo) throws IdCardException, PanCardException {
		PanCard pacrdno = panCardRepo.findBypanoNo(panNo);
		if (pacrdno == null) {
			throw new PanCardException("No user found with this pan card number ");
		}else {
            IdCard existingUsear =  pacrdno.getUsear();
			
			IdCardDto uddto = new IdCardDto();
			uddto.setName(existingUsear.getName());
			uddto.setDateOfBirth(existingUsear.getDateOfBirth());
			uddto.setGender(existingUsear.getGender());
			uddto.setAddress(existingUsear.getAddress());
			uddto.setCity(existingUsear.getCity());
			uddto.setState(existingUsear.getState());
			uddto.setPanCard(existingUsear.getPanCard());
			uddto.setAdharCard(existingUsear.getAdharCard());
			
			
			return uddto;
		}
	}

	@Override
	public CurrentUserSession loginUsear(UsearDto usear) throws LoginException {
		IdCard existingUser = idCardRepo.findBymemberUserName(usear.getUsername());

		if (existingUser == null) {
			throw new LoginException("Invalid credentials User does not exist with this username -" + usear.getUsername());	
		}
		

		Optional<CurrentUserSession> validCustomerSessionOpt = cuRepo.findById(existingUser.getId());

		if (validCustomerSessionOpt.isPresent() && existingUser.getMemberPassword().equals(usear.getUserPassword())) {
			cuRepo.delete(validCustomerSessionOpt.get());
		}

		if (existingUser.getMemberPassword().equals(usear.getUserPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getId(), key, isAdmin,
					LocalDateTime.now());

			cuRepo.save(currentUserSession);

			return currentUserSession;
		} else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logoutUsear(String key) throws LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to logout admin");
		}
		cuRepo.delete(loggedInUser);
		return "Logged Out !";
	}
	}
	