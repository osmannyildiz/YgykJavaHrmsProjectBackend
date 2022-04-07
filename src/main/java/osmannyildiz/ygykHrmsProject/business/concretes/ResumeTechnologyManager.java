package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeTechnologyService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeTechnologyDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.ResumeTechnology;

@Service
public class ResumeTechnologyManager implements IResumeTechnologyService {
	
	private IResumeTechnologyDao resumeTechnologyDao;

	@Autowired
	public ResumeTechnologyManager(IResumeTechnologyDao resumeTechnologyDao) {
		this.resumeTechnologyDao = resumeTechnologyDao;
	}

	@Override
	public DataResult<List<ResumeTechnology>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<ResumeTechnology>>(resumeTechnologyDao.getAllByResumeId(resumeId));
	}

	@Override
	public DataResult<ResumeTechnology> add(ResumeTechnology resumeTechnology) {
		return new SuccessDataResult<ResumeTechnology>(resumeTechnologyDao.save(resumeTechnology));
	}

	@Override
	public DataResult<ResumeTechnology> update(ResumeTechnology resumeTechnology) {
		return new SuccessDataResult<ResumeTechnology>(resumeTechnologyDao.save(resumeTechnology));
	}

	@Override
	public Result delete(ResumeTechnology resumeTechnology) {
		resumeTechnologyDao.delete(resumeTechnology);
		return new SuccessResult();
	}
}
