package com.wincovid.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Member;
import com.wincovid.module.VaccineInventory;

@Repository
public interface VaccineInventoryRepo extends JpaRepository<VaccineInventory, Integer>{
	
	 public List<VaccineInventory> findByDate(LocalDate date);
	 
	 @Query("from VaccineInventory where vaccineCount.vaccine.vaccninName=:vna")
	// @Query("from vaccineInventory Inner Join Vaccine ON VaccineInventory.vaccineInventoryId=:Vaccine.vaccineld WHERE vaccineName=:vna")
	 public List<VaccineInventory> findByVaccineName(@Param("vna") String vna);

}
