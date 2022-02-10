package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeExperience;

public interface IResumeExperienceDao extends JpaRepository<ResumeExperience, Integer> {
	
	@Query("from ResumeExperience where resume.jobSeekerUserId=:resumeId")
	List<ResumeExperience> getByResumeId(int resumeId);

}
