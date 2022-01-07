package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.ygykHrmsProject.business.abstracts.IUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

@Service
public class UserManager implements IUserService {
	
	private IUserDao userDao;

	@Autowired
	public UserManager(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}
	
}