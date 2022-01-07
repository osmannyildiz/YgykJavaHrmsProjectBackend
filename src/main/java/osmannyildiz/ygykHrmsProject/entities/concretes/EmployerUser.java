package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
