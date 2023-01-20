package com.wincovid.module;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String adminName;
	
	@Pattern(regexp = "^[0-9]{10}", message = "{user.invalid.contact}")
	@Column(unique = true)
	private String adminMobileNumber;
	
	@Email
	@Column(unique = true)
	private String adminUsername;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{user.invalid.password}")
	private String adminPassword;

}
