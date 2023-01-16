package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wincovid.dto.CurrentUserSession;
@Repository
public interface CurrentUserSessionRepo extends JpaRepository<CurrentUserSession, Integer>{
	public CurrentUserSession findByUuid(String uuid);
}
