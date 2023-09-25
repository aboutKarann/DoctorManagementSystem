package com.amdocs.dao;
import java.util.List;

import com.amdocs.main.*;
import com.amdocs.pojos.exception.*;
public interface DoctorDAO {
    int addDoctor(Doctor doctor);

    int deleteDoctor(int doctorId) throws DoctorNotFoundException;

    boolean updateDoctorFees(int doctorId, double newFees) throws DoctorNotFoundException;

    List<Doctor> showAllDoctors();

    List<Doctor> searchBySpecialization(String specialization) throws DoctorNotFoundException;

    List<Doctor> searchByFeesAndShift(String shift) throws DoctorNotFoundException;

    int countDoctorsByShift(String shift);
}