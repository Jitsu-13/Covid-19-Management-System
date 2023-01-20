package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.VaccineRegistration;

@Repository
public interface VaccineRegistrationRepo extends JpaRepository<VaccineRegistration, Integer>{
	public VaccineRegistration findBymobileno(String panoNo);
}
