package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeExperience;

public interface IResumeExperienceService {
	
	DataResult<List<ResumeExperience>> getAllByResumeId(int resumeId);
	DataResult<ResumeExperience> add(ResumeExperience resumeExperience);
	DataResult<ResumeExperience> update(ResumeExperience resumeExperience);
	Result delete(ResumeExperience resumeExperience);

}
