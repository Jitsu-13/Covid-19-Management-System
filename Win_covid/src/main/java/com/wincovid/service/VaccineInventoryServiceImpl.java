package com.wincovid.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.CurrentUserSession;
import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.exception.VaccineInventoryException;
import com.wincovid.module.VaccinationCenter;
import com.wincovid.module.Vaccine;
import com.wincovid.module.VaccineCount;
import com.wincovid.module.VaccineInventory;
import com.wincovid.repository.CurrentUserSessionRepo;
import com.wincovid.repository.VaccinationCenterRepo;
import com.wincovid.repository.VaccineCountRepo;
import com.wincovid.repository.VaccineInventoryRepo;
import com.wincovid.repository.VaccineRepo;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {
	
	@Autowired
 	private VaccineInventoryRepo vacInvRepo;
   
    @Autowired
    private VaccineRepo vaccineRepo;
    
    @Autowired
    private CurrentUserSessionRepo currentUserSessionRepo;
    
    @Autowired
    private VaccineCountRepo vaccineCountRepo;
    
    @Autowired
	private VaccinationCenterRepo vaccinationCenterRepo;

	@Override
	public VaccineInventory addVaccineCount(String key, int vaccineInventoryId,int vaccineCountID)
			throws VaccineInventoryException, LoginException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==true) {
			Optional<VaccineCount> vc = vaccineCountRepo.findById(vaccineCountID);
			Optional<VaccineInventory> vi = vacInvRepo.findById(vaccineInventoryId);
			if(vc.isPresent()&&vi.isPresent()) {
				VaccineCount vaccineCount =vc.get();
				VaccineInventory vaccineInventory =vi.get();
				vaccineInventory.getVaccineCount().add(vaccineCount);
				vacInvRepo.save(vaccineInventory);
				return vaccineInventory;
			}else {
				throw new VaccineInventoryException("No inventory found with this inventory ID ");
			}
			
		}else{
			throw new LoginException("Person logged in is not admin");
		}
		}else {
			throw new LoginException("To add vaccineCount login first ");
		}
	}

	@Override
	public VaccineInventory updateVaccinelnventory(String key, int vaccineInventoryId,LocalDate date) throws LoginException, VaccineInventoryException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
		if(loggedInUser.getAdmin()==true) {
			Optional<VaccineInventory> vv = vacInvRepo.findById(vaccineInventoryId);
			if(vv.isPresent()) {
				VaccineInventory vaccineInventory = vv.get();
				vaccineInventory.setDate(date);
				vacInvRepo.save(vaccineInventory);
				return vaccineInventory;
			}else {
				throw new VaccineInventoryException("No inventory found with this inventory ID ");
			}
			
		}else{
			throw new LoginException("Person logged in is not admin");
		}
		}else {
			throw new LoginException("To add vaccineCount login first ");
		}
	}

	@Override
	public VaccineInventory deleteVaccineInventory(String key, int vaccineInventoryid) throws LoginException, VaccineInventoryException {
		CurrentUserSession loggedInUser = currentUserSessionRepo.findByUuid(key);
		if(loggedInUser!=null) {
			if(loggedInUser.getAdmin()==true) {
				Optional<VaccineInventory> vv = vacInvRepo.findById(vaccineInventoryid);
				if(vv.isPresent()) {
					VaccineInventory vaccineInventory = vv.get();
					vacInvRepo.delete(vaccineInventory);
					return vaccineInventory;
				}else {
					throw new VaccineInventoryException("No inventory found with this inventory ID ");
				}
				
			}else{
				throw new LoginException("Person logged in is not admin");
			}
			}else {
				throw new LoginException("To delete inventory  login first ");
			}
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByCenter(int vaccinationCentercode) throws VaccineInventoryException,VaccinationCenterException {
		Optional<VaccinationCenter> vv = vaccinationCenterRepo.findById(vaccinationCentercode);
		if(vv.isEmpty()) {
			throw new VaccinationCenterException("Vaccination center Not found  ");
		}else {
			VaccinationCenter toget = vv.get();
			List<VaccineInventory> vil = toget.getVaccineInventory();
			if(vil.isEmpty()) {
				throw new VaccineInventoryException("No inventory found for this vaccination center ");
			}else {
				return vil;
			}
			
		}
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) throws VaccineInventoryException {
		List<VaccineInventory> vin = vacInvRepo.findByDate(date);
		
		if(vin.isEmpty()) {
			throw new VaccineInventoryException("No inventory found for this date ");
		}else {
			return vin;
		}
	}

	@Override
	public List<VaccineInventory> getVaccinelnventoryByVaccineName(String vaccineName) throws VaccineInventoryException {
//       List<VaccineInventory> vin = vacInvRepo.findByVaccineName(vaccineName);
//		if(vin.isEmpty()) {
//			throw new VaccineInventoryException("No inventory found for this vaccine ");
//		}else {
//			return vin;
//		}
		return null;
	}

	
	   
	    

//	@Override
//	public VaccineInventory getVaccineInventoryByCenter(int centerId) {
//		  
//	    return null;
//	}
//
//	@Override
//	public VaccineInventory addVaccineCount(VaccineInventory inv) {
//		VaccineInventory  vaccineInventory = vacInvRepo.save(inv);
//		return vaccineInventory;
//	}
//
//
//	@Override
//	public Boolean deleteVaccineInventory( VaccineInventory inv) throws VaccineInventoryException {
//		
//		  Optional <VaccineInventory> vaccineInventory = vacInvRepo.findById(inv.getVaccineInventoryId());
//		  
//		  if(vaccineInventory.isEmpty()) {
//			    throw new VaccineInventoryException("VaccineInventory is not found");
//		   }
//		    
//		  vacInvRepo.delete(vaccineInventory.get());
//		  
//		   
//		     return true;
//
//	}
//
//
//	@Override
//	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date)throws VaccineInventoryException {
//		   
//		List <VaccineInventory> ByDate = vacInvRepo.findByDate(date);
//		
//		 if(ByDate.isEmpty()) {
//			  throw new VaccineInventoryException("VaccineInventory is not found");
//		 }
//		   
//		   return ByDate;
//	
//	}
//
//	
//
//	
//
//	@Override
//	public VaccineInventory updateVaccinelnventory( VaccineInventory inv)throws VaccineInventoryException {
//			
//		  Optional <VaccineInventory> vaccineInventory = vacInvRepo.findById(inv.getVaccineInventoryId());
//		   
//		   if(vaccineInventory.isEmpty()) {
//			    throw new VaccineInventoryException("VaccineInventory is not found");
//		   }
//		    
//		   vacInvRepo.delete(vaccineInventory.get());
//		   VaccineInventory update =   vacInvRepo.save(inv);
//		   
//		     return update;
//	}
//
//	@Override
//	public List<VaccineInventory> getVaccinelnventoryByVaccine(Vaccine vaccine) {
//		
//		return null;
//	}


}
