package mk.ukim.finki.nsi.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.nsi.dms.dao.PatientDao;
import mk.ukim.finki.nsi.dms.model.Patient;
import mk.ukim.finki.nsi.dms.service.PatientService;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDao patientDao;

	public void addPatient(Patient patient) {
		patientDao.addPatient(patient);
	}

	public void deletePatient(int id) {
		patientDao.deletePatient(id);
	}

	public void updatePatient(Patient patient) {
		patientDao.updatePatient(patient);
	}

	public Patient getPatient(int id) {
		return patientDao.getPatient(id);
	}

	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}

	public void addOrUpdatePatient(Patient patient) {
		Patient p = getPatient(patient.getId());
		if (p == null) {
			addPatient(patient);
			return;
		}
		updatePatient(patient);
	}

	public void removeDoctorFromPatient(int patientId) {
		patientDao.removeDoctorFromPatient(patientId);
	}

	public List<Patient> getAllPatientsFromOtherDoctors(int doctorId) {
		return patientDao.getAllPatientsFromOtherDoctors(doctorId);
	}

	public void addDoctorToPatient(int patientId, int doctorId) {
		patientDao.addDoctorToPatient(patientId, doctorId);
	}

	public List<Patient> search(String keyword) {
		return patientDao.search(keyword);
	}

}
