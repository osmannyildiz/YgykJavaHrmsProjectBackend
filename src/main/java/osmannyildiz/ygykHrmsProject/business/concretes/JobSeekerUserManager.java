package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobSeekerUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobSeekerUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

@Service
public class JobSeekerUserManager implements IJobSeekerUserService {
	
	private IJobSeekerUserDao jobSeekerUserDao;

	@Autowired
	public JobSeekerUserManager(IJobSeekerUserDao jobSeekerUserDao) {
		this.jobSeekerUserDao = jobSeekerUserDao;
	}

	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		return new SuccessDataResult<List<JobSeekerUser>>(jobSeekerUserDao.findAll());
	}
	
}
