package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Admin;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	public Admin findByAdminUsername(String adminUsername);
}
