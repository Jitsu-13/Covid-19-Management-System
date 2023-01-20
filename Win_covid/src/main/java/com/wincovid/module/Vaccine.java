package com.wincovid.module;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vaccine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vaccineld;
	private String vaccninName;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VaccineCount vaccineCount;
}
