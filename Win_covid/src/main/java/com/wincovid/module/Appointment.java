package com.wincovid.module;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.wincovid.enums.SlotTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@NotNull(message="SlotTime can't be null")
	@Enumerated(EnumType.STRING)
	private SlotTime slotTime;
}
