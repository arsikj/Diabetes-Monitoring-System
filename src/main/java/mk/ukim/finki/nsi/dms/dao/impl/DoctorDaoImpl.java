package mk.ukim.finki.nsi.dms.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.nsi.dms.dao.DoctorDao;
import mk.ukim.finki.nsi.dms.model.Doctor;
import mk.ukim.finki.nsi.dms.model.Patient;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addDoctor(Doctor doctor) {
		getCurrentSession().save(doctor);
	}

	public void deleteDoctor(int id) {
		Doctor doctor = getDoctor(id);
		if (doctor != null)
			getCurrentSession().delete(doctor);
	}

	public void updateDoctor(Doctor doctor) {
		Doctor d = getDoctor(doctor.getId());
		if (d != null) {
			d.setId(doctor.getId());
			d.setUsername(doctor.getUsername());
			d.setPassword(doctor.getPassword());
			d.setAuthority(doctor.getAuthority());
			d.setName(doctor.getName());
			d.setEnabled(doctor.isEnabled());
			d.setPatients(doctor.getPatients());
			getCurrentSession().update(d);
		}
	}

	public Doctor getDoctor(int id) {
		return (Doctor) getCurrentSession().get(Doctor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> getAllDoctors() {
		return (List<Doctor>) getCurrentSession().createQuery("from doctor").list();
	}

	public Doctor getDoctorByUsername(String username) {
		return (Doctor) getCurrentSession().createCriteria(Doctor.class).add(Restrictions.eq("username", username))
				.uniqueResult();
	}

	public Doctor checkCredentials(String username, String password) {
		return (Doctor) getCurrentSession().createCriteria(Doctor.class).add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatientsByDoctorId(int id) {
		return (List<Patient>) getCurrentSession().createQuery("from Patient where doctor_id = :doctorId")
				.setParameter("doctorId", id).list();
	}

}
