package osmannyildiz.ygykHrmsProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IResumeDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.Resume;

@Service
public class ResumeManager implements IResumeService {
	
	private IResumeDao resumeDao;

	@Autowired
	public ResumeManager(IResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}
	
	@Override
	public DataResult<Resume> getById(int id) {
		Resume resume = resumeDao.findById(id).get();
		return new SuccessDataResult<Resume>(resume);
	}

	@Override
	public DataResult<Resume> add(Resume resume) {
		return new SuccessDataResult<Resume>(resumeDao.save(resume));
	}

	@Override
	public DataResult<Resume> update(Resume resume) {
		return new SuccessDataResult<Resume>(resumeDao.save(resume));
	}
	
}
