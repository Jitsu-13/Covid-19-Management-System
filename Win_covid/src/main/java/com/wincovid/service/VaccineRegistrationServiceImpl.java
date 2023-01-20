package com.wincovid.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.Member;
import com.wincovid.module.VaccineRegistration;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.VaccineRegistrationRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService{
	@Autowired
	private VaccineRegistrationRepo vaccineRegistrationRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;
	
	
	@Override
	public VaccineRegistration getVaccineRegistrationByMobileNo(String panoNo) throws VaccineRegistrationException {
		VaccineRegistration vaccineRegistration = vaccineRegistrationRepo.findBymobileno(panoNo);
		if(vaccineRegistration == null) {
			throw new VaccineRegistrationException("No vaccine registration found with this mobile number - "+panoNo);
		}else {
			return vaccineRegistration;
		}
	}

	@Override
	public List<Member> getAllMemberByMobileNo(String panoNo) throws VaccineRegistrationException {
		VaccineRegistration vaccineRegistration = vaccineRegistrationRepo.findBymobileno(panoNo);
		if(vaccineRegistration == null) {
			throw new VaccineRegistrationException("No vaccine registration found with this mobile number - "+panoNo);
		}else {
			List<Member> member = vaccineRegistration.getMember();
			if(member.isEmpty()) {
				throw new VaccineRegistrationException("No members found ");
			}
			return member;
		}
	}

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration vaccineRegistration)
			throws VaccineRegistrationException {
		VaccineRegistration registration = vaccineRegistrationRepo.findBymobileno(vaccineRegistration.getMobileno());
		if(registration == null) {
			return vaccineRegistrationRepo.save(vaccineRegistration);
		}else {
			throw new VaccineRegistrationException("Registration already exist with this mobile number  - "+vaccineRegistration.getMobileno());
		}
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(String key,VaccineRegistration vaccineRegistration)
			throws LoginException,VaccineRegistrationException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update vaccine registration");
		}

		else{
			Optional<VaccineRegistration> registration = vaccineRegistrationRepo.findById(loggedInUser.getUserId());
			VaccineRegistration vr = registration.get();
			vr.setPassword(vaccineRegistration.getPassword());
			vr.setMobileno(vaccineRegistration.getMobileno());
			return vaccineRegistrationRepo.save(vr);
		}
	}

	@Override
	public String deleteVaccineRegistration(String key) throws LoginException,VaccineRegistrationException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to delete Account");
		}
		Optional<VaccineRegistration> dto = vaccineRegistrationRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new VaccineRegistrationException("Invalid Usear Details, please login first");
		}
		
		VaccineRegistration existingUsear = dto.get();
			

		vaccineRegistrationRepo.delete(existingUsear);

		return "Account deleted..";
	}

	@Override
	public CurrentUserSession loginUsear(UsearDto usear) throws LoginException {
		VaccineRegistration registration = vaccineRegistrationRepo.findBymobileno(usear.getPanoNo());

		if (registration == null) {
			throw new LoginException("Wrong credentials provided ");	
		}
		

		Optional<CurrentUserSession> CurrentUserSessionOpt = currentUserSessionRepo.findById(registration.getRegistrationId());

		if (CurrentUserSessionOpt.isPresent() && registration.getPassword().equals(usear.getUserPassword())) {
			currentUserSessionRepo.delete(CurrentUserSessionOpt.get());
		}

		if (registration.getPassword().equals(usear.getUserPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(registration.getRegistrationId(), key, isAdmin,
					LocalDateTime.now());

			currentUserSessionRepo.save(currentUserSession);

			return currentUserSession;
		} else {
			throw new LoginException("Please Enter a valid password");
		}
	}

	@Override
	public String logoutUsear(String key) throws LoginException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to logout admin");
		}
		currentUserSessionRepo.delete(loggedInUser);
		return "Logged Out !";
	}

}
