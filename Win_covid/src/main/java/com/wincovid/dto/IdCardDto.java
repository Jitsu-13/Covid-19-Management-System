package com.wincovid.dto;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wincovid.module.AdharCard;
import com.wincovid.module.PanCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdCardDto {

	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String Name;
	
	private Date DateOfBirth ;
	
	private String Gender;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Address}")
	private String Address ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.City}")
	private String city ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.State}")
	private String State;
	
	private PanCard panCard;
	
	private AdharCard adharCard;

}
