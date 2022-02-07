package osmannyildiz.ygykHrmsProject.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_postings")
public class JobPosting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;

	@NotBlank
	@Column(name="description")
	protected String description;
	
	@Column(name="salary_min")
	protected double salaryMin;
	
	@Column(name="salary_max")
	protected double salaryMax;
	
	@Column(name="openings")
	protected int openings;
	
	@Column(name="date_published")
	protected LocalDate datePublished;
	
	@Column(name="application_deadline")
	protected LocalDate applicationDeadline;
	
	@Column(name="active")
	protected boolean active;
	
	@ManyToOne
	@JoinColumn(name="employer_id")
	protected EmployerUser employer;
	
	@ManyToOne
	@JoinColumn(name="job_position_id")
	protected JobPosition jobPosition;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	protected City city;

}
