package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeExperienceService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeExperienceDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeExperience;

@Service
public class ResumeExperienceManager implements IResumeExperienceService {
	
	private IResumeExperienceDao resumeExperienceDao;

	@Autowired
	public ResumeExperienceManager(IResumeExperienceDao resumeExperienceDao) {
		this.resumeExperienceDao = resumeExperienceDao;
	}

	@Override
	public DataResult<List<ResumeExperience>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<ResumeExperience>>(resumeExperienceDao.getAllByResumeId(resumeId));
	}

	@Override
	public DataResult<ResumeExperience> add(ResumeExperience resumeExperience) {
		return new SuccessDataResult<ResumeExperience>(resumeExperienceDao.save(resumeExperience));
	}

	@Override
	public DataResult<ResumeExperience> update(ResumeExperience resumeExperience) {
		return new SuccessDataResult<ResumeExperience>(resumeExperienceDao.save(resumeExperience));
	}

	@Override
	public Result delete(ResumeExperience resumeExperience) {
		resumeExperienceDao.delete(resumeExperience);
		return new SuccessResult();
	}

}
