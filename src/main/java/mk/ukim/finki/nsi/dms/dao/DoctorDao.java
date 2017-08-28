package mk.ukim.finki.nsi.dms.dao;

import java.util.List;

import mk.ukim.finki.nsi.dms.model.Doctor;
import mk.ukim.finki.nsi.dms.model.Patient;

public interface DoctorDao {

	public void addDoctor(Doctor doctor);

	public void deleteDoctor(int id);

	public void updateDoctor(Doctor doctor);

	public Doctor getDoctor(int id);

	public List<Doctor> getAllDoctors();

	public Doctor getDoctorByUsername(String username);

	public Doctor checkCredentials(String username, String password);
	
	public List<Patient> getAllPatientsByDoctorId(int id);
}
