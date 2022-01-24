package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;

public interface IJobPostingDao extends JpaRepository<JobPosting, Integer> {
	
	List<JobPosting> getByActive(boolean active);
	List<JobPosting> getByActiveOrderByApplicationDeadlineAsc(boolean active);
	List<JobPosting> getByActiveOrderByApplicationDeadlineDesc(boolean active);
	List<JobPosting> getByEmployerId(int employerId);
	List<JobPosting> getByActiveAndEmployerId(boolean active, int employerId);

}
