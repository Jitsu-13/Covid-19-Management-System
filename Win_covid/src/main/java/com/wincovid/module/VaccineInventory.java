package com.wincovid.module;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private VaccinationCenter VaccinationCenter;
	
	@OneToMany
	private List<VaccineCount> vaccineCount;
	
	
}
