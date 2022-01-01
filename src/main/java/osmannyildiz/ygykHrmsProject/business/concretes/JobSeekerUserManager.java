package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<JobSeekerUser> getAll() {
		return jobSeekerUserDao.findAll();
	}
	
}
