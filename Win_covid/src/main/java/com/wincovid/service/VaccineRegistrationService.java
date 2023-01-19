package com.wincovid.service;

import java.util.Set;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.dto.UsearDto;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.Member;
import com.wincovid.module.VaccineRegistration;

public interface VaccineRegistrationService {
    public VaccineRegistration getVaccineRegistrationByMobileNo(String panoNo) throws VaccineRegistrationException;
    
    public Set<Member> getAllMemberByMobileNo(String panoNo) throws VaccineRegistrationException;

    public VaccineRegistration addVaccineRegistration(VaccineRegistration vaccineRegistration) throws VaccineRegistrationException;

    public VaccineRegistration updateVaccineRegistration(String key,VaccineRegistration vaccineRegistration) throws LoginException,VaccineRegistrationException;

    public String deleteVaccineRegistration(String key) throws LoginException,VaccineRegistrationException;
    
    public CurrentUserSession loginUsear(UsearDto usear) throws LoginException;
	
   	public String logoutUsear(String key) throws LoginException;
}
