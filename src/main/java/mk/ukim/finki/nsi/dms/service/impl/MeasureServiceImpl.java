package mk.ukim.finki.nsi.dms.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mk.ukim.finki.nsi.dms.dao.MeasureDao;
import mk.ukim.finki.nsi.dms.model.Measure;
import mk.ukim.finki.nsi.dms.service.MeasureService;

@Service
@Transactional
public class MeasureServiceImpl implements MeasureService {

	@Autowired
	MeasureDao measureDao;

	public void addMeasure(Measure measure) {
		measureDao.addMeasure(measure);
	}

	public void deleteMeasure(int id) {
		measureDao.deleteMeasure(id);
	}

	public void updateMeasure(Measure measure) {
		measureDao.updateMeasure(measure);
	}

	public Measure getMeasure(int id) {
		return measureDao.getMeasure(id);
	}

	public List<Measure> getAllMeasures() {
		return measureDao.getAllMeasures();
	}

	public List<Measure> getAllMeasuresByPatientId(int id) {
		return measureDao.getAllMeasuresByPatientId(id);
	}

	public void addOrUpdateMeasure(Measure measure) {
		Measure m = getMeasure(measure.getId());
		if (m == null) {
			addMeasure(measure);
			return;
		}
		updateMeasure(measure);
	}

	public List<Measure> getAllMeasuresByPatientIdBetweenDates(int id, Date fromDate, Date toDate)
			throws HibernateException, ParseException {
		return measureDao.getAllMeasuresByPatientIdBetweenDates(id, fromDate, toDate);
	}

	public List<Measure> getAllCriticalMeasures() {
		return measureDao.getAllCriticalMeasures();
	}

}
