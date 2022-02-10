package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeLanguage;

public interface IResumeLanguageService {
	
	DataResult<List<ResumeLanguage>> getAllByResumeId(int resumeId);
	DataResult<ResumeLanguage> add(ResumeLanguage resumeLanguage);
	DataResult<ResumeLanguage> update(ResumeLanguage resumeLanguage);
	Result delete(ResumeLanguage resumeLanguage);

}
