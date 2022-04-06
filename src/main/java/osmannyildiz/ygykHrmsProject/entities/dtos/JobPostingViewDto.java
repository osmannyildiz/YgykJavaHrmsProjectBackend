package osmannyildiz.ygykHrmsProject.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingViewDto {
	
	protected int id;
	protected String employerCompanyName;
	protected String jobPositionName;
	protected int openings;
	protected LocalDate datePublished;
	protected LocalDate applicationDeadline;
	
	public JobPostingViewDto(JobPosting jobPosting) {
		this.id = jobPosting.getId();
		this.employerCompanyName = jobPosting.getEmployer().getCompanyName();
		this.jobPositionName = jobPosting.getPosition().getName();
		this.openings = jobPosting.getOpenings();
		this.datePublished = jobPosting.getDatePublished();
		this.applicationDeadline = jobPosting.getApplicationDeadline();
	}	

}
