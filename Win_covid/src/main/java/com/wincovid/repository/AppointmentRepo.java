package com.wincovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wincovid.module.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

}
