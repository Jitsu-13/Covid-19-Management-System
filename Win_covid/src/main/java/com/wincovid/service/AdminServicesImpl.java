package com.wincovid.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wincovid.dto.AdminDto;
import com.wincovid.dto.CurrentUserSession;
import com.wincovid.exception.AdminException;
import com.wincovid.exception.LoginException;
import com.wincovid.module.Admin;
import com.wincovid.repository.AdminRepo;
import com.wincovid.repository.CurrentUserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurrentUserSessionRepo cuRepo;
	//************************************signUp************************************************************************
	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		Admin existingUserName = adminRepo.findByAdminUsername(admin.getAdminUsername());

		if (existingUserName != null) {
			throw new AdminException("Admin already exist with this admin user name -  " + admin.getAdminUsername());
		}else {
			return adminRepo.save(admin);
		}
	}
	//************************************Update User********************************************************************
	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update a admin");
		}

		if (admin.getAdminId() == loggedInUser.getUserId()) {
			return adminRepo.save(admin);
		} else {
			throw new AdminException("Invalid Admin Details, please login first");
		}
	}
	//************************************Delete Account******************************************************************
	@Override
	public String deleteAdminAccount(String key) throws AdminException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to delete Account");
		}
		Optional<Admin> dto = adminRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new AdminException("Invalid Admin Details, please login first");
		}
		
		Admin existingAdmin = dto.get();
			

		adminRepo.delete(existingAdmin);

		return "Account deleted..";
	}
	//************************************find By key*********************************************************************
	@Override
	public Admin findBykey(String key) throws AdminException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update admin");
		}
		Optional<Admin> dto = adminRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new AdminException("Invalid Admin Details, please login first");
		}
		
		Admin existingAdmin = dto.get();
		
		return existingAdmin;
	}
	//************************************LogOut***************************************************************************
	@Override
	public String logoutAdmin(String key) throws LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to logout admin");
		}
		cuRepo.delete(loggedInUser);
		return "Logged Out !";
	}
	//************************************LogIn**************************************************************************
	@Override
	public CurrentUserSession loginAdmin(AdminDto admin) throws LoginException {
	
			Admin existingUser = adminRepo.findByAdminUsername(admin.getAdminUsername());

			if (existingUser == null) {
				throw new LoginException("Invalid credentials. Username does not exist with this username -" + admin.getAdminUsername());	
			}
			

			Optional<CurrentUserSession> validCustomerSessionOpt = cuRepo.findById(existingUser.getAdminId());

			if (validCustomerSessionOpt.isPresent() && existingUser.getAdminPassword().equals(admin.getAdminPassword())) {
				cuRepo.delete(validCustomerSessionOpt.get());
			}

			if (existingUser.getAdminPassword().equals(admin.getAdminPassword())) {

				String key = RandomString.make(6);

				Boolean isAdmin = true;

				CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getAdminId(), key, isAdmin,
						LocalDateTime.now());

				cuRepo.save(currentUserSession);

				return currentUserSession;
			} else {
				throw new LoginException("Please Enter a valid password");
			}	
		
	}

}
