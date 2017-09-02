package mk.ukim.finki.nsi.dms.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.nsi.dms.dao.PatientDao;
import mk.ukim.finki.nsi.dms.model.Patient;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addPatient(Patient patient) {
		getCurrentSession().save(patient);
	}

	public void deletePatient(int id) {
		Patient patient = getPatient(id);
		if (patient != null)
			getCurrentSession().delete(patient);

	}

	public void updatePatient(Patient patient) {
		Patient p = getPatient(patient.getId());
		if (p != null) {
			p.setAuthority(patient.getAuthority());
			p.setDoctor(patient.getDoctor());
			p.setEnabled(patient.isEnabled());
			p.setId(patient.getId());
			p.setMeasures(patient.getMeasures());
			p.setName(patient.getName());
			p.setPassword(patient.getPassword());
			p.setUsername(patient.getUsername());
			p.setBornDate(patient.getBornDate());
			p.setCity(patient.getCity());
			p.setCountry(patient.getCountry());
			getCurrentSession().update(p);
		}

	}

	public Patient getPatient(int id) {
		return (Patient) getCurrentSession().get(Patient.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatients() {
		return (List<Patient>) getCurrentSession().createQuery("from patient").list();
	}

	public Patient getPatientByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Patient checkCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeDoctorFromPatient(int patientId) {
		getCurrentSession().createQuery("update Patient set doctor_id = :doctor_id" + " where id = :patientId")
				.setParameter("doctor_id", 0).setParameter("patientId", patientId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatientsFromOtherDoctors(int doctorId) {
		return (List<Patient>) getCurrentSession().createQuery("from Patient where doctor_id != :doctorId")
				.setParameter("doctorId", doctorId).list();
	}

	public void addDoctorToPatient(int patientId, int doctorId) {
		getCurrentSession().createQuery("update Patient set doctor_id = :doctor_id" + " where id = :patientId")
				.setParameter("doctor_id", doctorId).setParameter("patientId", patientId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> search(String keyword) {
		return (List<Patient>) getCurrentSession().createQuery("from Patient where name like :keyword")
				.setParameter("keyword", "%" + keyword + "%").list();
	}

}
