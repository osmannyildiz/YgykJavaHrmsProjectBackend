package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name="job_seeker_users")
@PrimaryKeyJoinColumn(name="user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})
public class JobSeekerUser extends User {
	
	@NotBlank
	@Column(name="name")
	protected String name;

	@NotBlank
	@Column(name="surname")
	protected String surname;

	@NotBlank
	@Column(name="tckn")
	protected String tckn;
	
	@NotBlank
	@Column(name="birth_year")
	protected String birthYear;
	
	@OneToOne(mappedBy="user")
	protected Resume resume;

}
