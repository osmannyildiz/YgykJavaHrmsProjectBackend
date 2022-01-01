package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="job_seeker_users")
@PrimaryKeyJoinColumn(name="user_id")
public class JobSeekerUser extends User {
	
	@Column(name="name")
	protected String name;

	@Column(name="surname")
	protected String surname;

	@Column(name="tckn")
	protected String tckn;
	
	@Column(name="birth_year")
	protected String birthYear;
	
	public JobSeekerUser() {}

	public JobSeekerUser(int id, String email, String passwordHash, boolean emailVerified, String name, String surname, String tckn, String birthYear) {
		super(id, email, passwordHash, emailVerified);
		this.name = name;
		this.surname = surname;
		this.tckn = tckn;
		this.birthYear = birthYear;
	}

}
