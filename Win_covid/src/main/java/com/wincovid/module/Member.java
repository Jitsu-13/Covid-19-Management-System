package com.wincovid.module;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Member {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int memberId;

private boolean dose1status;

private boolean dose2status;

private LocalDate  dose1date;

private LocalDate  dose2date;

@OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
private Appointment appointment;

@OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
private VaccineRegistration vaccineRegistration;

@OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
private Vaccine vaccine;

@OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
private IdCard idCard;
}
