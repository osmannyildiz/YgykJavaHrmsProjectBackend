package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeEducation;

public interface IResumeEducationDao extends JpaRepository<ResumeEducation, Integer> {
	
	@Query("from ResumeEducation where resume.jobSeekerUserId=:resumeId")
	List<ResumeEducation> getAllByResumeId(int resumeId);

}
