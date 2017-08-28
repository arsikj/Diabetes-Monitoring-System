package mk.ukim.finki.nsi.dms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "doctor")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	private String username;

	private String name;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String authority;

	@JsonIgnore
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Patient> patients;
	
	public Doctor() {
	}
	
	public Doctor(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.patients = new ArrayList<Patient>();
		this.authority = "ROLE_USER";
		this.enabled = true;
	}
	
	public Doctor(String username, String password, String name, List<Patient> patients) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.patients = patients;
		this.authority = "ROLE_USER";
		this.enabled = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}
