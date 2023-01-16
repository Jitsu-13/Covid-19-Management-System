package com.wincovid.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.UsearException;
import com.wincovid.module.AdharCard;
import com.wincovid.module.PanCard;
import com.wincovid.module.Usear;
import com.wincovid.repository.AdharCardRepo;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.PanCardRepo;
import com.wincovid.repository.UsearRepo;
import net.bytebuddy.utility.RandomString;

@Service
public class UsearServicesImpl implements UsearServices{
	@Autowired
	private UsearRepo usearRepo;
	
	@Autowired
	private PanCardRepo panCardRepo;
	
	@Autowired
	private AdharCardRepo adharCardRepo;
	
	@Autowired
	private CurrentUserSessionRepo cuRepo;
	//************************************signUp************************************************************************
	@Override
	public Usear registerUsear(Usear usear) throws UsearException {
		Usear existingUserName = usearRepo.findBymemberUserName(usear.getMemberUserName());

		if (existingUserName != null) {
			throw new UsearException("Usear already exist with this admin user name -  " + usear.getMemberUserName());
		}else {
			return usearRepo.save(usear);
		}
	}
	//************************************Update User********************************************************************
	@Override
	public Usear updateUser(Usear usear, String key) throws UsearException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update a usear");
		}

		if (usear.getMemberId() == loggedInUser.getUserId()) {
			return usearRepo.save(usear);
		} else {
			throw new UsearException("Invalid usear Details, please login first");
		}
	}
	//************************************Delete Account******************************************************************
	@Override
	public String deleteUsearAccount(String key) throws UsearException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to delete Account");
		}
		Optional<Usear> dto = usearRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new UsearException("Invalid Usear Details, please login first");
		}
		
		Usear existingUsear = dto.get();
			

		usearRepo.delete(existingUsear);

		return "Account deleted..";
	}
	//************************************find By key*********************************************************************
	@Override
	public Usear findUsearBykey(String key) throws UsearException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update usear");
		}
		Optional<Usear> dto = usearRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new UsearException("Invalid usear Details, please login first");
		}
		
		Usear existingUsear = dto.get();
		
		return existingUsear;
	}
	//************************************find By Id*********************************************************************
	@Override
	public Usear findUsearById(int uid) throws UsearException {
		Optional<Usear> dto = usearRepo.findById(uid);
		if (dto.isEmpty()) {
			throw new UsearException("No user found with this user ID ");
		}
		Usear existingUsear = dto.get();
		return existingUsear;
	}
//****************************************************************************************************************************
	@Override
	public Usear findUsearAdharNo(String adharNo) throws UsearException,AdharCardException {
		AdharCard padrcad = adharCardRepo.findByadharNo(adharNo);
		if (padrcad == null) {
			throw new AdharCardException("No user found with this aadhaar card number ");
		}else {
			return padrcad.getUsear();
		}
	}

	@Override
	public Usear findUsearpanNo(String panNo) throws PanCardException {
		PanCard pacrdno = panCardRepo.findBypanoNo(panNo);
		if (pacrdno == null) {
			throw new PanCardException("No user found with this pan card number ");
		}else {
			return pacrdno.getUsear();
		}
	}
//****************************************************************************************************************************
	//************************************LogIn**************************************************************************
	@Override
	public CurrentUserSession loginUsear(UsearDto usear) throws LoginException {
		Usear existingUser = usearRepo.findBymemberUserName(usear.getUsername());

		if (existingUser == null) {
			throw new LoginException("Invalid credentials User does not exist with this username -" + usear.getUsername());	
		}
		

		Optional<CurrentUserSession> validCustomerSessionOpt = cuRepo.findById(existingUser.getMemberId());

		if (validCustomerSessionOpt.isPresent() && existingUser.getMemberPassword().equals(usear.getUserPassword())) {
			cuRepo.delete(validCustomerSessionOpt.get());
		}

		if (existingUser.getMemberPassword().equals(usear.getUserPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getMemberId(), key, isAdmin,
					LocalDateTime.now());

			cuRepo.save(currentUserSession);

			return currentUserSession;
		} else {
			throw new LoginException("Please Enter a valid password");
		}	
	}
	//************************************LogOut***************************************************************************
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
