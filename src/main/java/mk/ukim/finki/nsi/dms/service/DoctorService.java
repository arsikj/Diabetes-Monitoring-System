package mk.ukim.finki.nsi.dms.service;

import java.util.List;

import mk.ukim.finki.nsi.dms.model.Doctor;
import mk.ukim.finki.nsi.dms.model.Patient;

public interface DoctorService {
	
	public void addDoctor(Doctor doctor);
	
	public void deleteDoctor(int id);
	
	public void updateDoctor(Doctor doctor);
	
	public Doctor getDoctor(int id);
	
	public List<Doctor> getAllDoctors();
	
	public void addOrUpdateDoctor(Doctor doctor);
	
	public Doctor findDoctorByUsername(String username);
	
	public boolean signInDoctor(Doctor doctor);
	
	public List<Patient> getAllPatientsByDoctorId(int id);

}
