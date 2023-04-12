package com.wincovid.service;

import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.MemberException;
import com.wincovid.module.IdCard;

public interface IdCardService {
	public IdCard addIdCard(String key,IdCard idCard) throws LoginException,IdCardException,MemberException,VaccineRegistrationException;
	
	public IdCard findIdCardByAdharNo(String adharNo) throws IdCardException,AdharCardException;
	
	public IdCard findIdCardBypanNo(String panNo) throws IdCardException,PanCardException;
}
