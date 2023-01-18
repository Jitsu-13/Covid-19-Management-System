package com.wincovid.module;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccinationCentercode;
	private String centername;
	private String address;
	private String city;
	private String state;
	private String pincode;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Appointment> appointments;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private VaccineInventory vaccineInventory;
}
