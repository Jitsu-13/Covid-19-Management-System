package com.wincovid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.MemberUpdateDto;
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

@Service
public class MemberServicesImpl implements MemberServices{
	@Autowired
	private VaccineRegistrationRepo vaccineRegistrationRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;
	
	@Autowired
	private IdCardRepo idCardRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private AdharCardRepo adharCardRepo;
	
	@Autowired
	private PanCardRepo panCardRepo;
	
	
	@Override
	public Member getMemberByld(String key, int idcardid) throws LoginException, MemberException, IdCardException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			Optional<IdCard> idCard = idCardRepo.findById(idcardid);
			if(idCard.isEmpty()) {
				throw new IdCardException("No ID card found with this card ID - "+idcardid);
			}else {
				Member mm = memberRepo.findByidCard(idcardid);
				return mm;
			}
		}else{
			throw new LoginException("Person logged in is a admin not member ");
		}
		}else {
			throw new LoginException("To get the details login first ");
		}
	}

	@Override
	public Member getMemberByAdharNo(String adharno) throws MemberException,AdharCardException {
		Member mm = memberRepo.findByAdharcardNo(adharno);
		if (mm == null) {
			throw new MemberException("No member found with this aadhaar card number ");
		}else {
			return mm;
		}
		
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberException,PanCardException {
		Member mm = memberRepo.findByPanNo(panNo);
		if (mm == null) {
			throw new MemberException("No member found with this aadhaar card number ");
		}else {
			return mm;
		}
	}

	@Override
	public Member addMember(String key, Member member) throws LoginException, MemberException,VaccineRegistrationException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			Optional<Member> mm = memberRepo.findById(member.getMemberId());
			Optional<VaccineRegistration> vaccineRegistration = vaccineRegistrationRepo.findById(loggedInUser.getUserId());
			if(vaccineRegistration.isPresent()) {
				if(mm.isPresent()) {
					throw new MemberException("Member already present ");
				}else {
					VaccineRegistration vr = vaccineRegistration.get();
					 final Member x = member;
					 vr.getMember().add(x);
					 memberRepo.save(x);
					 return x;
				}
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
	public Member updateMember(String key,int idcardid, MemberUpdateDto MemberUpdateDto) throws LoginException, MemberException,VaccineRegistrationException,IdCardException  {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
			
		if(loggedInUser.getAdmin()==false) {
			Member mm = memberRepo.findByidCard(idcardid);
			mm.getIdCard().setName(MemberUpdateDto.getName());
			mm.getIdCard().setAddress(MemberUpdateDto.getAddress());
			mm.getIdCard().setCity(MemberUpdateDto.getCity());
			mm.getIdCard().setDateOfBirth(MemberUpdateDto.getDateOfBirth());
			mm.getIdCard().setGender(MemberUpdateDto.getGender());
			mm.getIdCard().setState(MemberUpdateDto.getState());
			
			memberRepo.save(mm);
			return mm;
			
			}else{
			throw new LoginException("Person logged in is a admin");
		}
		
		
		}else {
			throw new LoginException("To update the details login first ");
		}
	}

	@Override
	public Member deleteMember(String key,int idcardid) throws LoginException, MemberException,IdCardException,VaccineRegistrationException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
			
		if(loggedInUser.getAdmin()==false) {
			Optional<VaccineRegistration> vaccineRegistration = vaccineRegistrationRepo.findById(loggedInUser.getUserId());
			if(vaccineRegistration.isPresent()) {
				Member mm = memberRepo.findByidCard(idcardid);
				VaccineRegistration vr = vaccineRegistration.get();
				vr.getMember().remove(mm);
				memberRepo.delete(mm);
				return mm;
			}else {
				throw new VaccineRegistrationException("No vaccine registration found ");
			}
			}else{
			throw new LoginException("Person logged in is a admin");
		}
		}else {
			throw new LoginException("To update the details login first ");
		}
	}

}
