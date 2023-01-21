package com.wincovid.module;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class VaccineCount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int VaccineCountid;
	
	private int quantity;
	
	private double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Vaccine vaccine;
	
//	@ManyToOne
//	@JsonIgnore
//	private VaccineInventory vaccineInventoryl;
}
