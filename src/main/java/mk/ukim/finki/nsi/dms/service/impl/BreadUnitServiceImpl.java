package mk.ukim.finki.nsi.dms.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.nsi.dms.dao.BreadUnitDao;
import mk.ukim.finki.nsi.dms.model.BreadUnit;
import mk.ukim.finki.nsi.dms.service.BreadUnitService;

@Service
@Transactional
public class BreadUnitServiceImpl implements BreadUnitService{
	
	@Autowired
	BreadUnitDao breadUnitDao;

	public void addBreadUnit(BreadUnit breadUnit) {
		breadUnitDao.addBreadUnit(breadUnit);
	}

	public void deleteBreadUnit(int id) {
		breadUnitDao.deleteBreadUnit(id);
	}

	public void updateBreadUnit(BreadUnit breadUnit) {
		breadUnitDao.updateBreadUnit(breadUnit);
	}

	public BreadUnit getBreadUnit(int id) {
		return breadUnitDao.getBreadUnit(id);
	}

	public List<BreadUnit> getAllBreadUnits() {
		return breadUnitDao.getAllBreadUnits();
	}

	public List<BreadUnit> getAllBreadUnitsByPatientId(int id) {
		return breadUnitDao.getAllBreadUnitsByPatientId(id);
	}

	public List<BreadUnit> getAllBreadUnitsByPatientIdBetweenDates(int id, Date fromDate, Date toDate)
			throws HibernateException, ParseException {
		return breadUnitDao.getAllMBreadUnitsByPatientIdBetweenDates(id, fromDate, toDate);
	}

	public void addOrUpdateBreadUnit(BreadUnit breadUnit) {
		BreadUnit bu = getBreadUnit(breadUnit.getId());
		if(bu == null) {
			addBreadUnit(breadUnit);
			return;
		}
		updateBreadUnit(breadUnit);
		
	}

}
