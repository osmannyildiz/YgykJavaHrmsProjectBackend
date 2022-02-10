package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeImage;

public interface IResumeImageDao extends JpaRepository<ResumeImage, Integer> {
	
	@Query("from ResumeImage where resume.jobSeekerUserId=:resumeId")
	List<ResumeImage> getByResumeId(int resumeId);

}
