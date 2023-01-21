package com.wincovid.service;

import com.wincovid.exception.AppointmentException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.MemberException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.module.Appointment;

public interface AppointmentServices {
    
    public Appointment addAppointment(Appointment app ,String key,String adharNo,int vaccinationCentercode) throws MemberException,AppointmentException,LoginException,VaccinationCenterException ;

    public Appointment updateAppointment(Appointment app,String key,String adharNo,int vaccinationCentercode) throws MemberException, AppointmentException, LoginException,VaccinationCenterException ;

    public Appointment deleteAppointment(Appointment app,String key,String adharNo,int vaccinationCentercode) throws MemberException, AppointmentException, LoginException,VaccinationCenterException ;
    
    public Appointment getAppointment(int bookingid) throws AppointmentException;

}
