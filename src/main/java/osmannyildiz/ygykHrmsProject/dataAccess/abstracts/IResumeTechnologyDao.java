package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeTechnology;

public interface IResumeTechnologyDao extends JpaRepository<ResumeTechnology, Integer> {
	
	@Query("from ResumeTechnology where resume.jobSeekerUserId=:resumeId")
	List<ResumeTechnology> getByResumeId(int resumeId);

}
