package com.wincovid.service;

import com.wincovid.exception.AppointmentException;
import com.wincovid.module.Appointment;

public interface AppointmentServices {
    public Appointment getAppointment(Integer bookingid) throws AppointmentException;

    public String addAppointment(Appointment app) throws AppointmentException;

    public String updateAppointment(Appointment app) throws AppointmentException;

    public String deleteAppointment(Appointment app) throws AppointmentException;

}
