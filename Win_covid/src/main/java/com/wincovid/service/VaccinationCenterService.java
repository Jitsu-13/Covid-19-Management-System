package com.wincovid.service;

import com.wincovid.exception.VaccinationCenterException;
import com.wincovid.module.VaccinationCenter;

public interface VaccinationCenterService {
    public VaccinationCenter getVaccineCenter(Integer vccode) throws VaccinationCenterException;

    public String addVaccineCenter(VaccinationCenter vc) throws VaccinationCenterException;

    public String updateVaccineCenter(VaccinationCenter vc) throws VaccinationCenterException;

    public String deleteVaccineCenter(VaccinationCenter vc) throws VaccinationCenterException;

}
