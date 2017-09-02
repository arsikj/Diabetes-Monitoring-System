package mk.ukim.finki.nsi.dms.service;

import java.util.List;

import mk.ukim.finki.nsi.dms.model.Patient;

public interface PatientService {

	public void addPatient(Patient patient);

	public void deletePatient(int id);

	public void updatePatient(Patient patient);

	public Patient getPatient(int id);

	public List<Patient> getAllPatients();

	public void addOrUpdatePatient(Patient patient);
	
	public void removeDoctorFromPatient(int patientId);
	
	public List<Patient> getAllPatientsFromOtherDoctors(int doctorId);
	
	public void addDoctorToPatient(int patientId, int doctorId);
	
	public List<Patient> search(String keyword);
}
