package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeTechnology;

public interface IResumeTechnologyService {
	
	DataResult<List<ResumeTechnology>> getAllByResumeId(int resumeId);
	DataResult<ResumeTechnology> add(ResumeTechnology resumeTechnology);
	DataResult<ResumeTechnology> update(ResumeTechnology resumeTechnology);
	Result delete(ResumeTechnology resumeTechnology);

}
