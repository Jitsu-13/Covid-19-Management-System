package com.wincovid.service;

import com.wincovid.dto.MemberUpdateDto;
import com.wincovid.exception.AdharCardException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.MemberException;
import com.wincovid.exception.PanCardException;
import com.wincovid.exception.VaccineRegistrationException;
import com.wincovid.module.Member;

public interface MemberServices {
	
    public Member getMemberByld(String key,int idcardid) throws LoginException,MemberException,IdCardException;
    
    public Member getMemberByAdharNo(String adharno) throws MemberException,AdharCardException;
    
    public Member getMemberByPanNo(String panNo) throws MemberException,PanCardException;
    
    public Member addMember(String key,Member member) throws LoginException,MemberException,VaccineRegistrationException;
    
    public Member updateMember(String key,int idcardid,MemberUpdateDto MemberUpdateDto) throws LoginException,IdCardException,MemberException,VaccineRegistrationException;
    
    public Member deleteMember(String key,int idcardid) throws LoginException,MemberException,IdCardException,VaccineRegistrationException;

}
