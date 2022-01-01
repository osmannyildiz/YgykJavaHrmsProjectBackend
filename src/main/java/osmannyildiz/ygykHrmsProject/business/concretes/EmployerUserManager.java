package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<EmployerUser> getAll() {
		return employerUserDao.findAll();
	}
	
}
