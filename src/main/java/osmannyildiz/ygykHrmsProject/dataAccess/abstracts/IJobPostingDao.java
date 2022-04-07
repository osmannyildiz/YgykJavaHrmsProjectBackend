package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;

public interface IJobPostingDao extends JpaRepository<JobPosting, Integer> {
	
	List<JobPosting> getAllByActive(boolean active);
	List<JobPosting> getAllByActiveOrderByApplicationDeadlineAsc(boolean active);
	List<JobPosting> getAllByActiveOrderByApplicationDeadlineDesc(boolean active);
	List<JobPosting> getAllByEmployerId(int employerId);
	List<JobPosting> getAllByActiveAndEmployerId(boolean active, int employerId);

}
