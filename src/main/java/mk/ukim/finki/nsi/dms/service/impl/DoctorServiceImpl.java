package mk.ukim.finki.nsi.dms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.nsi.dms.dao.DoctorDao;
import mk.ukim.finki.nsi.dms.model.Doctor;
import mk.ukim.finki.nsi.dms.model.Patient;
import mk.ukim.finki.nsi.dms.service.DoctorService;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDao doctorDao;

	public void addDoctor(Doctor doctor) {
		doctorDao.addDoctor(doctor);
	}

	public void deleteDoctor(int id) {
		doctorDao.deleteDoctor(id);
	}

	public void updateDoctor(Doctor doctor) {
		doctorDao.updateDoctor(doctor);
	}

	public Doctor getDoctor(int id) {
		return doctorDao.getDoctor(id);
	}

	public List<Doctor> getAllDoctors() {
		return doctorDao.getAllDoctors();
	}

	public void addOrUpdateDoctor(Doctor doctor) {
		Doctor d = getDoctor(doctor.getId());
		if (d == null) {
			addDoctor(doctor);
			return;
		}
		updateDoctor(doctor);
	}

	public Doctor findDoctorByUsername(String username) {
		return doctorDao.getDoctorByUsername(username);
	}

	public boolean signInDoctor(Doctor doctor) {
		return doctorDao.checkCredentials(doctor.getUsername(), doctor.getPassword()) != null;
	}

	public List<Patient> getAllPatientsByDoctorId(int id) {
		return doctorDao.getAllPatientsByDoctorId(id);
	}

}
