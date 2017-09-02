package mk.ukim.finki.nsi.dms.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import mk.ukim.finki.nsi.dms.model.Measure;

public interface MeasureDao {

	public void addMeasure(Measure measure);

	public void deleteMeasure(int id);

	public void updateMeasure(Measure measure);

	public Measure getMeasure(int id);

	public List<Measure> getAllMeasures();

	public List<Measure> getAllMeasuresByPatientId(int id);

	public List<Measure> getAllMeasuresByPatientIdBetweenDates(int id, Date fromDate, Date toDate) throws HibernateException, ParseException;

	public List<Measure> getAllCriticalMeasures();
	
}
