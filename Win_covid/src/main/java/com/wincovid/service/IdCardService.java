package com.wincovid.service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.IdCardDto;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.module.IdCard;

public interface IdCardService {
	public IdCard addIdCard(IdCard idCard) throws IdCardException;
	
	public IdCardDto findIdCardByAdharNo(String adharNo) throws IdCardException,AdharCardException;
	
	public IdCardDto findIdCardBypanNo(String panNo) throws IdCardException,PanCardException;
	
    public CurrentUserSession loginUsear(UsearDto usear) throws LoginException;
	
	public String logoutUsear(String key) throws LoginException;
}
