package com.amdocs.dao;
import com.amdocs.main.*;
import com.amdocs.pojos.exception.*;

import java.util.*;

public class DoctorDAOimpl implements DoctorDAO {
	 List<Doctor> doctors=new ArrayList<>();
	@Override
	public int addDoctor(Doctor doctor) {		
		doctors.add(doctor);
		return doctor.getDoctorId();
	}
	@Override
	public  int deleteDoctor(int doctorId) throws DoctorNotFoundException {
		for(int i=0;i<doctors.size();i++)
		{
			if(doctors.get(i).getDoctorId()==doctorId)
			{
				doctors.remove(i);
				return doctorId;
			}
		}
		return -1;
	}
	@Override
	public boolean updateDoctorFees(int doctorId,double fees) throws DoctorNotFoundException{
		for(Doctor doctor: doctors)
		{
			if(doctor.getDoctorId()==doctorId)
			{
				doctor.setFees(fees);
				return true;
			}
		}
		return false;
	}
	@Override
	public List<Doctor> showAllDoctors(){	

		return doctors;
	}
	@Override
	public  List<Doctor> searchBySpecialization(String specialization) throws DoctorNotFoundException{		
		List<Doctor> doctorResult=new ArrayList<>();
		for(Doctor doctor: doctors)
		{
			if(doctor.getSpecialization().equalsIgnoreCase(specialization))
			{
				doctorResult.add(doctor);
			}
		}
		return doctorResult;
	}
	@Override
	public  List<Doctor>searchByFeesAndShift(String shift) throws DoctorNotFoundException{
		List<Doctor> result=new ArrayList<>();
		double lowest=Double.MAX_VALUE;
		for(Doctor doctor: doctors)
		{
			if(doctor.getAvailableShift().equalsIgnoreCase(shift))
			{
				if(doctor.getFees()<lowest)
				{
					lowest=doctor.getFees();
					result.clear();
					result.add(doctor);
				}
				else if(doctor.getFees()==lowest)
				{
					result.add(doctor);
				}
			}
		}
		return result;
		
	}
	@Override
	public int countDoctorsByShift(String shift) {
		int count = 0;
        for (Doctor doctor : doctors) {
            if (doctor.getAvailableShift().equalsIgnoreCase(shift)) {
                count++;
            }
        }
        return count;
		
	}



}
