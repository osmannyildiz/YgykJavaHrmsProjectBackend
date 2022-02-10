package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeEducation;

public interface IResumeEducationService {
	
	DataResult<List<ResumeEducation>> getAllByResumeId(int resumeId);
	DataResult<ResumeEducation> add(ResumeEducation resumeEducation);
	DataResult<ResumeEducation> update(ResumeEducation resumeEducation);
	Result delete(ResumeEducation resumeEducation);

}
