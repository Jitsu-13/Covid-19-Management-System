package com.wincovid.module;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Entity
public class AdharCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adharId;
	
	@Pattern(regexp = "^[0-9]{12}", message = "{user.invalid.adharNo}")
	@Column(unique = true)
	private String adharNo;
	
	private String fringerprints;
	
	private String irisscan;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "adharCard")
	@JsonIgnore
	private IdCard idCard;

}
