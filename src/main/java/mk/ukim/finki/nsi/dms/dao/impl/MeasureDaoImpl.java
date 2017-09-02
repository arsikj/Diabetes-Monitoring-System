package mk.ukim.finki.nsi.dms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.nsi.dms.dao.MeasureDao;
import mk.ukim.finki.nsi.dms.model.Measure;

@Repository
public class MeasureDaoImpl implements MeasureDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addMeasure(Measure measure) {
		getCurrentSession().save(measure);
	}

	public void deleteMeasure(int id) {
		Measure m = getMeasure(id);
		if (m != null)
			getCurrentSession().delete(m);
	}

	public void updateMeasure(Measure measure) {
		Measure m = getMeasure(measure.getId());
		if (m != null) {
			m.setDateAdded(measure.getDateAdded());
			m.setLevel(measure.getLevel());
			m.setId(measure.getId());
			m.setPatient(measure.getPatient());
			getCurrentSession().update(m);
		}
	}

	public Measure getMeasure(int id) {
		return (Measure) getCurrentSession().get(Measure.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Measure> getAllMeasures() {
		return (List<Measure>) getCurrentSession().createQuery("from Measure").list();
	}

	@SuppressWarnings("unchecked")
	public List<Measure> getAllMeasuresByPatientId(int id) {
		return (List<Measure>)getCurrentSession().createQuery("from Measure where patient_id = :patientId")
				.setParameter("patientId", id).list();
	}

	@SuppressWarnings("unchecked")
	public List<Measure> getAllMeasuresByPatientIdBetweenDates(int id, Date fromDate, Date toDate)
			throws HibernateException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = df.format(fromDate);
		String endDate = df.format(toDate);

		return (List<Measure>)getCurrentSession()
				.createQuery("from Measure where patient_id = :patientId and dateAdded between :startDate and :endDate")
				.setParameter("patientId", id).setDate("startDate", df.parse(startDate))
				.setDate("endDate", df.parse(endDate)).list();
	}

	@SuppressWarnings("unchecked")
	public List<Measure> getAllCriticalMeasures() {
		return (List<Measure>)getCurrentSession().createQuery("from Measure where level > :level").setParameter("level", 239).list();
	}

}
