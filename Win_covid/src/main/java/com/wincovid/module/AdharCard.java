package com.wincovid.module;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.wincovid.enums.Fingerprint;
import com.wincovid.enums.Iris;
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

//	@NotNull(message="FingerPrints can't be null")
	@Enumerated(EnumType.STRING)
	private Fingerprint fringerprints;

//	@NotNull(message="Iris can't be null")
	@Enumerated(EnumType.STRING)
	private Iris irisscan;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "adharCard")
	@JsonIgnore
	private IdCard idCard;

}
