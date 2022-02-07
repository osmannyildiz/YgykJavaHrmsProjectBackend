package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

public interface IUserService {
	
	DataResult<List<User>> getAll();
	DataResult<User> getById(int id);
	DataResult<User> getByEmail(String email);
	DataResult<User> add(User user);
	DataResult<User> update(User user);
	DataResult<Boolean> userWithEmailExists(String email);

}
