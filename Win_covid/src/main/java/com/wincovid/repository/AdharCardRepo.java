package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.AdharCard;
import com.wincovid.module.Admin;

@Repository
public interface AdharCardRepo extends JpaRepository<AdharCard, Integer>{
	public AdharCard findByadharNo(String adharNo);
}
