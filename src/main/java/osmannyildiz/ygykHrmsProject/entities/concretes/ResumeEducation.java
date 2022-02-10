package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="resume_educations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "resume"})
public class ResumeEducation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;

	@NotBlank
	@Column(name="school_name")
	protected String schoolName;

	@Column(name="department")
	protected String department;

	@NotBlank
	@Column(name="start_year")
	protected String startYear;
	
	@Column(name="graduation_year")
	protected String graduationYear;
	
	@ManyToOne
	@JoinColumn(name="resume_id")
	protected Resume resume;

}
