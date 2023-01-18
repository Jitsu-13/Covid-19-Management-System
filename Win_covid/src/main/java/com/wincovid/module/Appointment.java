package com.wincovid.module;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingid;
	
	@Pattern(regexp = "^[0-9]{10}", message = "{user.invalid.contact}")
	private String mobileno;
	
	private LocalDate dateofbooking;
	
	private boolean bookingstatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private VaccinationCenter VaccinationCenter;
	
	//private slot: Slot
}
