package com.wincovid.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.exception.AppointmentException;
import com.wincovid.exception.IdCardException;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.MemberException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.module.Appointment;
import com.wincovid.module.IdCard;
import com.wincovid.module.Member;
import com.wincovid.module.VaccinationCenter;
import com.wincovid.module.VaccineRegistration;
import com.wincovid.repository.AdharCardRepo;
import com.wincovid.repository.AppointmentRepo;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.IdCardRepo;
import com.wincovid.repository.MemberRepo;
import com.wincovid.repository.PanCardRepo;
import com.wincovid.repository.VaccinationCenterRepo;
import com.wincovid.repository.VaccineRegistrationRepo;

@Service
public class AppointmentServicesImpl implements AppointmentServices{
    @Autowired
	private AppointmentRepo appointmentRepo;
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
	
	@Autowired
	private VaccinationCenterRepo vaccinationCenterRepo;
	
	@Override
	public Appointment addAppointment(Appointment app, String key, String adharNo,int vaccinationCentercode)
			throws MemberException, AppointmentException, LoginException,VaccinationCenterException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			
			
			Member mm = memberRepo.findByAdharcardNo(adharNo);
			if (mm == null) {
				throw new MemberException("No member found with this aadhaar card number ");
			}else {
				VaccineRegistration vaccineRegistration = vaccineRegistrationRepo.findBymobileno(app.getMobileno());
				if(vaccineRegistration.getMember().contains(mm)) {
					
					
					if(mm.isDose1status()) {
						mm.setDose2status(app.isBookingstatus());
					}else {
						mm.setDose1date(app.getDateofbooking());
						mm.setDose1status(app.isBookingstatus());
						mm.setDose2date(app.getDateofbooking().plusMonths(3));
					}
					memberRepo.save(mm);
					app.setMember(mm);
					Optional<VaccinationCenter> vc = vaccinationCenterRepo.findById(vaccinationCentercode);
					if(vc.isPresent()) {
						app.setVaccinationCenter(vc.get());
						vc.get().getAppointments().add(app);
						appointmentRepo.save(app);
						return app;
					}else {
						throw new VaccinationCenterException("No vaccination center found with this vaccination center code ");
					}
				}else {
					throw new MemberException("Invalid mobile number ");	
				}
			}
		}else{
			throw new LoginException("Person logged in is a admin not member ");
		}
		}else {
			throw new LoginException("To book appointment login first ");
		}
	}

	@Override
	public Appointment updateAppointment(Appointment app, String key, String adharNo,int vaccinationCentercode)
			throws MemberException, AppointmentException, LoginException,VaccinationCenterException  {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			
			
			Member mm = memberRepo.findByAdharcardNo(adharNo);
			if (mm == null) {
				throw new MemberException("No member found with this aadhaar card number ");
			}else {
				VaccineRegistration vaccineRegistration = vaccineRegistrationRepo.findBymobileno(app.getMobileno());
				if(vaccineRegistration.getMember().contains(mm)) {
					
					
					if(mm.isDose1status()) {
						mm.setDose2status(app.isBookingstatus());
					}else {
						mm.setDose1date(app.getDateofbooking());
						mm.setDose1status(app.isBookingstatus());
						mm.setDose2date(app.getDateofbooking().plusMonths(3));
					}
					memberRepo.save(mm);
					app.setMember(mm);
					Optional<VaccinationCenter> vc = vaccinationCenterRepo.findById(vaccinationCentercode);
					if(vc.isPresent()) {
						app.setVaccinationCenter(vc.get());
						vc.get().getAppointments().add(app);
						appointmentRepo.save(app);
						return app;
					}else {
						throw new VaccinationCenterException("No vaccination center found with this vaccination center code ");
					}
				}else {
					throw new MemberException("Invalid mobile number ");	
				}
			}
		}else{
			throw new LoginException("Person logged in is a admin not member ");
		}
		}else {
			throw new LoginException("To book appointment login first ");
		}
	}

	@Override
	public Appointment deleteAppointment(Appointment app, String key, String adharNo,int vaccinationCentercode)
			throws MemberException, AppointmentException, LoginException,VaccinationCenterException  {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==false) {
			
			
			Member mm = memberRepo.findByAdharcardNo(adharNo);
			if (mm == null) {
				throw new MemberException("No member found with this aadhaar card number ");
			}else {
				VaccineRegistration vaccineRegistration = vaccineRegistrationRepo.findBymobileno(app.getMobileno());
				if(vaccineRegistration.getMember().contains(mm)) {
					
					
					if(mm.isDose1status()) {
						mm.setDose2status(false);
					}else {
						mm.setDose1date(null);
						mm.setDose1status(false);
						mm.setDose2date(null);
					}
					mm.setAppointment(null);
					memberRepo.save(mm);
					app.setMember(mm);
					Optional<VaccinationCenter> vc = vaccinationCenterRepo.findById(vaccinationCentercode);
					if(vc.isPresent()) {
						app.setVaccinationCenter(vc.get());
						vc.get().getAppointments().remove(app);
						appointmentRepo.delete(app);
						return app;
					}else {
						throw new VaccinationCenterException("No vaccination center found with this vaccination center code ");
					}
				}else {
					throw new MemberException("Invalid mobile number ");	
				}
			}
		}else{
			throw new LoginException("Person logged in is a admin not member ");
		}
		}else {
			throw new LoginException("To book appointment login first ");
		}
	}

	@Override
	public Appointment getAppointment(int bookingid) throws AppointmentException {
		Optional<Appointment> ap = appointmentRepo.findById(bookingid);
		if(ap.isPresent()) {
			Appointment appointment = ap.get();
			return appointment;
		}else {
			throw new AppointmentException("No appointment found with this booking ID ");
		}
	}
   
}
