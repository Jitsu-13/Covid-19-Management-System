package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Admin;
import com.wincovid.module.IdCard;
@Repository
public interface IdCardRepo extends JpaRepository<IdCard, Integer>{
//	public IdCard findBymemberUserName(String memberUserName);
}
