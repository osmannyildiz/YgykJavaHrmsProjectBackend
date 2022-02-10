package osmannyildiz.ygykHrmsProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="resumes")
//@PrimaryKeyJoinColumn(name="job_seeker_user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class Resume {
	
	@Id
	@Column(name="job_seeker_user_id")
	protected int jobSeekerUserId;
	
	@NotBlank
	@Column(name="cover_text")
	protected String coverText;

	@NotBlank
	@Column(name="linkedin")
	protected String linkedin;

	@NotBlank
	@Column(name="github")
	protected String github;
	
	@OneToOne
	@JoinColumn(name="job_seeker_user_id")
	protected JobSeekerUser user;
	
	@OneToMany(mappedBy="resume")
	protected List<ResumeEducation> educations;
	
	@OneToMany(mappedBy="resume")
	protected List<ResumeExperience> experiences;
	
	@OneToMany(mappedBy="resume")
	protected List<ResumeLanguage> languages;
	
	@OneToMany(mappedBy="resume")
	protected List<ResumeTechnology> technologies;
	
	@OneToMany(mappedBy="resume")
	protected List<ResumeImage> images;

}
