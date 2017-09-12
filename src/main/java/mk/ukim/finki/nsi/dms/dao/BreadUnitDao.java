package mk.ukim.finki.nsi.dms.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import mk.ukim.finki.nsi.dms.model.BreadUnit;

public interface BreadUnitDao {
	
	public void addBreadUnit(BreadUnit breadUnit);

	public void deleteBreadUnit(int id);

	public void updateBreadUnit(BreadUnit breadUnit);

	public BreadUnit getBreadUnit(int id);

	public List<BreadUnit> getAllBreadUnits();

	public List<BreadUnit> getAllBreadUnitsByPatientId(int id);

	public List<BreadUnit> getAllMBreadUnitsByPatientIdBetweenDates(int id, Date fromDate, Date toDate) throws HibernateException, ParseException;
}
