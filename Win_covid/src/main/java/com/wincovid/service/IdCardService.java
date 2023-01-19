package com.wincovid.service;

import com.wincovid.dto.IdCardDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.module.IdCard;

public interface IdCardService {
	public IdCard addIdCard(IdCard idCard) throws IdCardException;
	
	public IdCardDto findIdCardByAdharNo(String adharNo) throws IdCardException,AdharCardException;
	
	public IdCardDto findIdCardBypanNo(String panNo) throws IdCardException,PanCardException;
}
