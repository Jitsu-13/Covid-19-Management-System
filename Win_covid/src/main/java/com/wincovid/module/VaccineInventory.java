package com.wincovid.module;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class VaccineInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccineInventoryId;
	
	private LocalDate date;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private VaccinationCenter VaccinationCenter;
//	
	@OneToOne
	private VaccineCount vaccineCount;
	
	
}
