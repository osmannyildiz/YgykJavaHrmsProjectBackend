package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeImage;

public interface IResumeImageService {
	
	DataResult<List<ResumeImage>> getAllByResumeId(int resumeId);
	DataResult<ResumeImage> add(ResumeImage resumeImage);
	DataResult<ResumeImage> update(ResumeImage resumeImage);
	Result delete(ResumeImage resumeImage);

}
