package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
	
	@Override
	public DataResult<JobSeekerUser> add(JobSeekerUser user) {
		return new SuccessDataResult<JobSeekerUser>(jobSeekerUserDao.save(user));
	}
	
	@Override
	public DataResult<JobSeekerUser> update(JobSeekerUser user) {
		return new SuccessDataResult<JobSeekerUser>(jobSeekerUserDao.save(user));
	}

	@Override
	public DataResult<Boolean> userWithTcknExists(String tckn) {
		boolean exists = jobSeekerUserDao.countByTckn(tckn) > 0;
		return new SuccessDataResult<Boolean>(exists);
	}
	
}
