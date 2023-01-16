package com.wincovid.service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.UsearException;
import com.wincovid.module.Usear;

public interface UsearServices {
	public Usear registerUsear(Usear usear) throws UsearException;

	public Usear updateUser(Usear usear,String key) throws UsearException, LoginException;

	public String deleteUsearAccount(String key) throws UsearException, LoginException;

	public Usear findUsearBykey(String key) throws UsearException, LoginException;
	
	public Usear findUsearById(int uid) throws UsearException;
	
	public Usear findUsearAdharNo(String adharNo) throws UsearException,AdharCardException;
	
	public Usear findUsearpanNo(String panNo) throws PanCardException;
	
	public CurrentUserSession loginUsear(UsearDto usear) throws LoginException;
	
	public String logoutUsear(String key) throws LoginException;
}
