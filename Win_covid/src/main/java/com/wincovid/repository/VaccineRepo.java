package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Vaccine;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Integer>{
	  public Vaccine findByvaccninName(String vaccineName);
}
