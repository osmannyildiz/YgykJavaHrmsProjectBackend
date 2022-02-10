package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeLanguage;

public interface IResumeLanguageDao extends JpaRepository<ResumeLanguage, Integer> {
	
	@Query("from ResumeLanguage where resume.jobSeekerUserId=:resumeId")
	List<ResumeLanguage> getByResumeId(int resumeId);

}
