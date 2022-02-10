package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeEducationService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeEducationDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeEducation;

@Service
public class ResumeEducationManager implements IResumeEducationService {
	
	private IResumeEducationDao resumeEducationDao;

	@Autowired
	public ResumeEducationManager(IResumeEducationDao resumeEducationDao) {
		this.resumeEducationDao = resumeEducationDao;
	}

	@Override
	public DataResult<List<ResumeEducation>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<ResumeEducation>>(resumeEducationDao.getByResumeId(resumeId));
	}

	@Override
	public DataResult<ResumeEducation> add(ResumeEducation resumeEducation) {
		return new SuccessDataResult<ResumeEducation>(resumeEducationDao.save(resumeEducation));
	}

	@Override
	public DataResult<ResumeEducation> update(ResumeEducation resumeEducation) {
		return new SuccessDataResult<ResumeEducation>(resumeEducationDao.save(resumeEducation));
	}

	@Override
	public Result delete(ResumeEducation resumeEducation) {
		resumeEducationDao.delete(resumeEducation);
		return new SuccessResult();
	}

}
