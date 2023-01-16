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

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Entity
public class Usear {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberId;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String memberName;
	
	private Date memberDateOfBirth ;
	
	private String memberGender;
	
	@Size(min = 2, max = 20, message = "{user.invalid.Address}")
	private String memberAddress ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.City}")
	private String membercity ;
	
	@Size(min = 2, max = 20, message = "{user.invalid.State}")
	private String memberState ;
	
	@Pattern(regexp = "^[0-9]{10}", message = "{user.invalid.contact}")
	@Column(unique = true)
	private String memberMobileNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PanCard panCard;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AdharCard adharCard;
	
	@Email
	@Column(unique = true)
	private String memberUserName;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{user.invalid.password}")
	private String memberPassword;

	public Usear() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usear(int memberId, @Size(min = 2, max = 20, message = "{user.invalid.Name}") String memberName,
			String memberDateOfBirth, String memberGender,
			@Size(min = 2, max = 20, message = "{user.invalid.Address}") String memberAddress,
			@Size(min = 2, max = 20, message = "{user.invalid.City}") String membercity,
			@Size(min = 2, max = 20, message = "{user.invalid.State}") String memberState,
			@Pattern(regexp = "^[0-9]{10}", message = "{user.invalid.contact}") String memberMobileNumber,
			PanCard panCard, AdharCard adharCard, @Email String memberUserName,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{user.invalid.password}") String memberPassword) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberDateOfBirth = Date.valueOf(memberDateOfBirth);
		this.memberGender = memberGender;
		this.memberAddress = memberAddress;
		this.membercity = membercity;
		this.memberState = memberState;
		this.memberMobileNumber = memberMobileNumber;
		this.panCard = panCard;
		this.adharCard = adharCard;
		this.memberUserName = memberUserName;
		this.memberPassword = memberPassword;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberDateOfBirth() {
		return memberDateOfBirth;
	}

	public void setMemberDateOfBirth(String memberDateOfBirth) {
		this.memberDateOfBirth = Date.valueOf(memberDateOfBirth);;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMembercity() {
		return membercity;
	}

	public void setMembercity(String membercity) {
		this.membercity = membercity;
	}

	public String getMemberState() {
		return memberState;
	}

	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}

	public String getMemberMobileNumber() {
		return memberMobileNumber;
	}

	public void setMemberMobileNumber(String memberMobileNumber) {
		this.memberMobileNumber = memberMobileNumber;
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
		return "Usear [memberId=" + memberId + ", memberName=" + memberName + ", memberDateOfBirth=" + memberDateOfBirth
				+ ", memberGender=" + memberGender + ", memberAddress=" + memberAddress + ", membercity=" + membercity
				+ ", memberState=" + memberState + ", memberMobileNumber=" + memberMobileNumber + ", panCard=" + panCard
				+ ", adharCard=" + adharCard + ", memberUserName=" + memberUserName + ", memberPassword="
				+ memberPassword + "]";
	}
	
	
}
