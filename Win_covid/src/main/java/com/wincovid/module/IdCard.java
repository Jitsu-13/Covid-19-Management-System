package com.wincovid.module;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Entity
public class IdCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String Name;
	
	private Date DateOfBirth ;
	
	private String Gender;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Address}")
	private String Address ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.City}")
	private String city ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.State}")
	private String State ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PanCard panCard;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AdharCard adharCard;
	
	@Email
	@Column(unique = true)
	private String memberUserName;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{user.invalid.password}")
	private String memberPassword;

	public IdCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdCard(int id, @Size(min = 2, max = 20, message = "{user.invalid.Name}") String name, String dateOfBirth,
			String gender, @Size(min = 2, max = 20, message = "{user.invalid.Address}") String address,
			@Size(min = 2, max = 20, message = "{user.invalid.City}") String city,
			@Size(min = 2, max = 20, message = "{user.invalid.State}") String state, PanCard panCard,
			AdharCard adharCard, @Email String memberUserName,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{user.invalid.password}") String memberPassword) {
		super();
		Id = id;
		Name = name;
		DateOfBirth = Date.valueOf(dateOfBirth);;
		Gender = gender;
		Address = address;
		this.city = city;
		State = state;
		this.panCard = panCard;
		this.adharCard = adharCard;
		this.memberUserName = memberUserName;
		this.memberPassword = memberPassword;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = Date.valueOf(dateOfBirth);;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public PanCard getPanCard() {
		return panCard;
	}

	public void setPanCard(PanCard panCard) {
		this.panCard = panCard;
	}

	public AdharCard getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(AdharCard adharCard) {
		this.adharCard = adharCard;
	}

	public String getMemberUserName() {
		return memberUserName;
	}

	public void setMemberUserName(String memberUserName) {
		this.memberUserName = memberUserName;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	@Override
	public String toString() {
		return "IdCard [Id=" + Id + ", Name=" + Name + ", DateOfBirth=" + DateOfBirth + ", Gender=" + Gender
				+ ", Address=" + Address + ", city=" + city + ", State=" + State + ", memberUserName=" + memberUserName
				+ ", memberPassword=" + memberPassword + "]";
	}


	
	
}
