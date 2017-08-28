package mk.ukim.finki.nsi.dms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "measure")
public class Measure {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int level;

	private Date dateAdded;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Patient patient;

	public Measure() {

	}

	public Measure(int level, Date dateAdded) {
		this.level = level;
		this.dateAdded = dateAdded;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
