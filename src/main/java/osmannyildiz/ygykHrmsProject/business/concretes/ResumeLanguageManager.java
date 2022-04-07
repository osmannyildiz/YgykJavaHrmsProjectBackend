package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeLanguageService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeLanguageDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeLanguage;

@Service
public class ResumeLanguageManager implements IResumeLanguageService {
	
	private IResumeLanguageDao resumeLanguageDao;

	@Autowired
	public ResumeLanguageManager(IResumeLanguageDao resumeLanguageDao) {
		this.resumeLanguageDao = resumeLanguageDao;
	}

	@Override
	public DataResult<List<ResumeLanguage>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<ResumeLanguage>>(resumeLanguageDao.getAllByResumeId(resumeId));
	}

	@Override
	public DataResult<ResumeLanguage> add(ResumeLanguage resumeLanguage) {
		return new SuccessDataResult<ResumeLanguage>(resumeLanguageDao.save(resumeLanguage));
	}

	@Override
	public DataResult<ResumeLanguage> update(ResumeLanguage resumeLanguage) {
		return new SuccessDataResult<ResumeLanguage>(resumeLanguageDao.save(resumeLanguage));
	}

	@Override
	public Result delete(ResumeLanguage resumeLanguage) {
		resumeLanguageDao.delete(resumeLanguage);
		return new SuccessResult();
	}
	
}
