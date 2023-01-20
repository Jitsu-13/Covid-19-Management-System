package com.wincovid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wincovid.module.Member;
@Repository
public interface MemberRepo extends JpaRepository<Member, Integer>{
	
@Query("from Member where idCard.Id=:idcardid")
public Member findByidCard(@Param("idcardid") int idcardid);

@Query("from Member where idCard.adharCard.adharNo=:adarno")
public Member findByAdharcardNo(@Param("adarno") String adarno);

@Query("from Member where idCard.panCard.panoNo=:panno")
public Member findByPanNo(@Param("panno") String panno);

}
