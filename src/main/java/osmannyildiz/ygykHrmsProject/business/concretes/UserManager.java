package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

@Service
public class UserManager implements IUserService {
	
	private IUserDao userDao;

	@Autowired
	public UserManager(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll());
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(userDao.getById(id));
	}
	
	@Override
	public DataResult<User> add(User user) {
		return new SuccessDataResult<User>(userDao.save(user));
	}

	@Override
	public DataResult<User> update(User user) {
		return new SuccessDataResult<User>(userDao.save(user));
	}

	@Override
	public DataResult<Boolean> userWithEmailExists(String email) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "emailVerified");
		User exampleUser = new User();
		exampleUser.setEmail(email);
		Example<User> example = Example.of(exampleUser, matcher);
		boolean exists = userDao.exists(example);
		return new SuccessDataResult<Boolean>(exists);
	}
	
}
