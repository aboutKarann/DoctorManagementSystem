package com.amdocs.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.amdocs.dao.DoctorDAO;
import com.amdocs.dao.DoctorDAOimpl;
import com.amdocs.main.*;
import com.amdocs.pojos.exception.DoctorNotFoundException;

public class DoctorMenuDriven {
	
	public static void main(String[] args){
		DoctorDAO system=new DoctorDAOimpl();
		Scanner sc=new Scanner(System.in);
		do {
			
			System.out.println("1. Add new doctor\r\n"
					+ "2. Update doctor fees\r\n"
					+ "3. Delete doctor\r\n"
					+ "4. View all doctors\r\n"
					+ "5. Find doctor by specialization\r\n"
					+ "6. Find doctors who’s fees is less than all doctors of given shift\r\n"
					+ "7. Count number of doctors by shift\r\n"
					+ "8. Exit\r\n"
					+ "");
			
			System.out.println("Enter your choice:");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				
				System.out.println("To Add new doctor");
				System.out.println("Enter Doctor Name: ");
				String doctorName=sc.next();
				System.out.println("Enter Doctor Mobile No.");
				String mobileNumber=sc.next();
				System.out.println("Enter Specialization");
				String specialization=sc.next();
				System.out.println("Enter Available Shift");
				String availableShift=sc.next();
				System.out.println("Enter Fees");
				double fees=sc.nextDouble();
				Doctor newDoctor=new Doctor(system.showAllDoctors().size()+1,doctorName,mobileNumber,specialization,availableShift,fees);
				int doctorID=system.addDoctor(newDoctor);
				System.out.println("Doctor added with id: "+ doctorID);
				break;
				
			case 2:
				System.out.println("Enter Doctor Id");
				int updateDoctorId=sc.nextInt();
				System.out.println("Enter New Fees: ");
				double newFees=sc.nextDouble();
				boolean updateResult;
				try {
					updateResult = system.updateDoctorFees(updateDoctorId, newFees);
					if(updateResult)
					{
						System.out.println("Doctor fees has been updated");
					}
					else
					{
						throw new DoctorNotFoundException("No Doctor Found");
					}
				} catch (DoctorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
				
			case 3:
				System.out.println("Enter doctor id");
				int deleteDoctorId=sc.nextInt();
				int deleteId;
				try {
					deleteId = system.deleteDoctor(deleteDoctorId);
					if(deleteId==-1)
					{
						throw new DoctorNotFoundException("No Doctor Found");
					}
					else
					{
						System.out.println("Doctor with id"+ deleteId + " has been removed");
					}
				} catch (DoctorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
			case 4:
				System.out.println("view all doctor");
				List<Doctor> allDoctors=system.showAllDoctors();
				if(allDoctors.isEmpty())
				{
					System.out.println("No doctors Found");
				}
				else
				{
					System.out.println("List of Doctors: ");
					for(Doctor doctor: allDoctors)
					{
						System.out.println(doctor.getDoctorName() + "," + doctor.getDoctorId() + "," + doctor.getMobileNumber() + "," + doctor.getSpecialization() + "," + doctor.getFees());
					}
				}
				break;	
			case 5:
				System.out.println("Enter Specialization to search");
				String searchSpecialization=sc.next();
				List<Doctor> specializationResult;
				try {
					specializationResult = system.searchBySpecialization(searchSpecialization);
					if(specializationResult.isEmpty())
					{
						throw new DoctorNotFoundException("No Doctor Found");
					}
					else
					{
						System.out.println("Doctors with the specialization"+ searchSpecialization + " :");
						for(Doctor doctor : specializationResult)
						{
							System.out.println(doctor.getDoctorName() + "," + doctor.getDoctorId() + "," + doctor.getMobileNumber() + "," + doctor.getSpecialization() + "," + doctor.getFees());
						}
					}
				} catch (DoctorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
			case 6:
				System.out.println("Enter the shift");
				String searchShift=sc.next();
				List<Doctor> feesResult;
				try {
					feesResult = system.searchByFeesAndShift(searchShift);
					if(feesResult.isEmpty())
					{
						throw new DoctorNotFoundException("No Doctor Found");
					}
					else
					{
						System.out.println("Doctors with the specialization"+ feesResult + " :");
						for(Doctor doctor : feesResult)
						{
							System.out.println(doctor.getDoctorName() + "," + doctor.getDoctorId() + "," + doctor.getMobileNumber() + "," + doctor.getSpecialization() + "," + doctor.getFees());
						}
					}
				} catch (DoctorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				break;
			case 7:
				System.out.print("Enter Shift to Count Doctors: ");
                String countShift = sc.next();
                int doctorCount = system.countDoctorsByShift(countShift);
                System.out.println("Number of doctors working in shift " + countShift + ": " + doctorCount);
                break;
			case 8:
				System.out.println("Program Exit");
                System.exit(0);
			}
			
		}
		while(true);
		
		
		
//		list.forEach(data->{
//			System.out.println(data);
//		});
		
	}
}