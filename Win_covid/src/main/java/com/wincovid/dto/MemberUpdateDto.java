package com.wincovid.dto;

import java.sql.Date;

import javax.validation.constraints.Size;

import com.wincovid.enums.gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {
	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String Name;
	
	private Date DateOfBirth ;
	
	private gender Gender;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Address}")
	private String Address ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.City}")
	private String city ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.State}")
	private String State ;
}
