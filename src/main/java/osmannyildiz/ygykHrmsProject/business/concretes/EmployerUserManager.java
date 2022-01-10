package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IEmployerUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IEmployerUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;

@Service
public class EmployerUserManager implements IEmployerUserService {
	
	private IEmployerUserDao employerUserDao;

	@Autowired
	public EmployerUserManager(IEmployerUserDao employerUserDao) {
		this.employerUserDao = employerUserDao;
	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {
		return new SuccessDataResult<List<EmployerUser>>(employerUserDao.findAll());
	}
	
	@Override
	public DataResult<EmployerUser> getById(int id) {
		return new SuccessDataResult<EmployerUser>(employerUserDao.getById(id));
	}

	@Override
	public DataResult<EmployerUser> add(EmployerUser user) {
		return new SuccessDataResult<EmployerUser>(employerUserDao.save(user));
	}
	
}
