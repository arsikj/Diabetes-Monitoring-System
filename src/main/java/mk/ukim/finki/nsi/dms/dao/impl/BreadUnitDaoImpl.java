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

import mk.ukim.finki.nsi.dms.dao.BreadUnitDao;
import mk.ukim.finki.nsi.dms.model.BreadUnit;

@Repository
public class BreadUnitDaoImpl implements BreadUnitDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addBreadUnit(BreadUnit breadUnit) {
		getCurrentSession().save(breadUnit);
	}

	public void deleteBreadUnit(int id) {
		BreadUnit breadUnit = getBreadUnit(id);
		if (breadUnit != null)
			getCurrentSession().delete(breadUnit);
	}

	public void updateBreadUnit(BreadUnit breadUnit) {
		BreadUnit bu = getBreadUnit(breadUnit.getId());
		if (bu != null) {
			bu.setDateAdded(breadUnit.getDateAdded());
			bu.setLevel(breadUnit.getLevel());
			bu.setId(breadUnit.getId());
			bu.setPatient(breadUnit.getPatient());
			getCurrentSession().update(bu);
		}
	}

	public BreadUnit getBreadUnit(int id) {
		return (BreadUnit) getCurrentSession().get(BreadUnit.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BreadUnit> getAllBreadUnits() {
		return (List<BreadUnit>) getCurrentSession().createQuery("from BreadUnit").list();
	}

	@SuppressWarnings("unchecked")
	public List<BreadUnit> getAllBreadUnitsByPatientId(int id) {
		return (List<BreadUnit>) getCurrentSession().createQuery("from BreadUnit where patient_id = :patientId")
				.setParameter("patientId", id).list();
	}

	@SuppressWarnings("unchecked")
	public List<BreadUnit> getAllMBreadUnitsByPatientIdBetweenDates(int id, Date fromDate, Date toDate)
			throws HibernateException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = df.format(fromDate);
		String endDate = df.format(toDate);

		return (List<BreadUnit>) getCurrentSession()
				.createQuery(
						"from BreadUnit where patient_id = :patientId and dateAdded between :startDate and :endDate")
				.setParameter("patientId", id).setDate("startDate", df.parse(startDate))
				.setDate("endDate", df.parse(endDate)).list();
	}

}
