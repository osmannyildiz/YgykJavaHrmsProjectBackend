package osmannyildiz.ygykHrmsProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employer_users")
@PrimaryKeyJoinColumn(name="user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobPostings"})
public class EmployerUser extends User {
	
	@NotBlank
	@Column(name="company_name")
	protected String companyName;

	@NotBlank
	@Column(name="website")
	protected String website;

	@NotBlank
	@Column(name="phone")
	protected String phone;
	
	@Column(name="system_user_verified")
	protected boolean systemUserVerified;
	
	@OneToMany(mappedBy="employer")
	protected List<JobPosting> jobPostings;

}
