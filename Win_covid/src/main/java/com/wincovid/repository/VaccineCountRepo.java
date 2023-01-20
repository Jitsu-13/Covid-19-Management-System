package com.wincovid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.VaccineCount;

@Repository
public interface VaccineCountRepo extends JpaRepository<VaccineCount, Integer>{
//	@Query("select VaccineInventory from VaccineCount where vaccine.vaccninName=:vna")
//	 public List<VaccineInventory> findByVaccineName(@Param("vna") String vna);
}
