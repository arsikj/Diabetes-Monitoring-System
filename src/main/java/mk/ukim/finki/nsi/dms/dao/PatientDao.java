package mk.ukim.finki.nsi.dms.dao;

import java.util.List;

import mk.ukim.finki.nsi.dms.model.Patient;

public interface PatientDao {

	public void addPatient(Patient patient);

	public void deletePatient(int id);

	public void updatePatient(Patient patient);

	public Patient getPatient(int id);

	public List<Patient> getAllPatients();

	public Patient getPatientByUsername(String username);

	public Patient checkCredentials(String username, String password);
	
	public void removeDoctorFromPatient(int patientId);
}
