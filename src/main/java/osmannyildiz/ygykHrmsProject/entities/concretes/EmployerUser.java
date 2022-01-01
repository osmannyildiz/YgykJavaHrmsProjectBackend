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
@Table(name="employer_users")
@PrimaryKeyJoinColumn(name="user_id")
public class EmployerUser extends User {
	
	@Column(name="company_name")
	protected String companyName;

	@Column(name="website")
	protected String website;

	@Column(name="phone")
	protected String phone;
	
	@Column(name="system_user_verified")
	protected boolean systemUserVerified;
	
	public EmployerUser() {}

	public EmployerUser(int id, String email, String passwordHash, boolean emailVerified, String companyName, String website, String phone, boolean systemUserVerified) {
		super(id, email, passwordHash, emailVerified);
		this.companyName = companyName;
		this.website = website;
		this.phone = phone;
		this.systemUserVerified = systemUserVerified;
	}

}
