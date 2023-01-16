package com.wincovid.service;

import com.wincovid.dto.AdminDto;
import com.wincovid.dto.CurrentUserSession;
import com.wincovid.exception.AdminException;
import com.wincovid.exception.LoginException;
import com.wincovid.module.Admin;

public interface AdminServices {
	public Admin registerAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin,String key) throws AdminException, LoginException;

	public String deleteAdminAccount(String key) throws AdminException, LoginException;

	public Admin findBykey(String key) throws AdminException, LoginException;
	
	public CurrentUserSession loginAdmin(AdminDto admin) throws LoginException;
	
	public String logoutAdmin(String key) throws LoginException;
}
