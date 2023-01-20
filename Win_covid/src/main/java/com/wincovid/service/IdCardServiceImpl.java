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
import com.wincovid.exception.MemberException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.AdharCard;
import com.wincovid.module.IdCard;
import com.wincovid.module.Member;
import com.wincovid.module.PanCard;
import com.wincovid.module.VaccineRegistration;
import com.wincovid.repository.AdharCardRepo;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.IdCardRepo;
import com.wincovid.repository.MemberRepo;
import com.wincovid.repository.PanCardRepo;
import com.wincovid.repository.VaccineRegistrationRepo;

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
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private VaccineRegistrationRepo vaccineRegistrationRepo;

	@Override
	public IdCard addIdCard(String key,IdCard idCard) throws LoginException,IdCardException,MemberException,VaccineRegistrationException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			Optional<VaccineRegistration> vaccineRegistration = vaccineRegistrationRepo.findById(loggedInUser.getUserId());
			if(vaccineRegistration.isPresent()) {
					VaccineRegistration vr = vaccineRegistration.get();
					Member mm1 = new Member();
					mm1.setIdCard(idCard);
					
					vr.getMember().add(mm1);
					memberRepo.save(mm1);
					return idCard;
				
			}else {
				throw new VaccineRegistrationException("No vaccine registration found ");
			}
		}else{
			throw new LoginException("Person logged in is a admin");
		}
		}else {
			throw new LoginException("To get the details login first ");
		}
	}

	@Override
	public IdCard findIdCardByAdharNo(String adharNo) throws IdCardException, AdharCardException {
		AdharCard adharcard = adharCardRepo.findByadharNo(adharNo);
		if (adharcard == null) {
			throw new AdharCardException("No user found with this aadhaar card number ");
		}else {
			IdCard existingUsear =  adharcard.getIdCard();	
			return existingUsear;
	}
	}

	@Override
	public IdCard findIdCardBypanNo(String panNo) throws IdCardException, PanCardException {
		PanCard pacrdno = panCardRepo.findBypanoNo(panNo);
		if (pacrdno == null) {
			throw new PanCardException("No user found with this pan card number ");
		}else {
            IdCard existingUsear =  pacrdno.getIdCard();
			return existingUsear;
		}
	}
	
	}
	
