package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Admin;
import com.wincovid.module.Usear;
@Repository
public interface UsearRepo extends JpaRepository<Usear, Integer>{
	public Usear findBymemberUserName(String memberUserName);
}
