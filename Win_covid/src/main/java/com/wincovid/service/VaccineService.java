package com.wincovid.service;


import com.wincovid.exception.LoginException;
import com.wincovid.exception.VaccineException;
import com.wincovid.module.Vaccine;

import java.util.List;

public interface VaccineService {
    public Vaccine getVaccineByName(String vaccineName) throws VaccineException;

    public Vaccine getVaccineById(int vaccineId) throws VaccineException;

    public Vaccine addVaccine(Vaccine vaccine,String key) throws VaccineException,LoginException;

    public Vaccine updateVaccine(Vaccine vaccine,String key) throws VaccineException,LoginException;

    public String deleteVaccine(int vaccineId,String key) throws VaccineException,LoginException;

    public List<Vaccine> getAllVaccine() throws  VaccineException;
}
