package com.wincovid.module;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccinationCentercode;
	
	@Size(min = 2, max = 20, message = "{VaccinationCenter.invalid.centername}")
	private String centername;
	
	@Size(min = 2, max = 20, message = "{VaccinationCenter.invalid.address}")
	private String address;
	
	@Size(min = 2, max = 20, message = "{VaccinationCenter.invalid.city}")
	private String city;
	
	@Size(min = 2, max = 20, message = "{VaccinationCenter.invalid.state}")
	private String state;
	
	@Size(min = 6, message = "{VaccinationCenter.invalid.pincode}")
	private String pincode;
	
	@OneToMany
	@JsonIgnore
	private List<Appointment> appointments;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<VaccineInventory> vaccineInventory;
}
