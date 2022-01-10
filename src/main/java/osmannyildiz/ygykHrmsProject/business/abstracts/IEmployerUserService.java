package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

public interface IEmployerUserService {
	
	DataResult<List<EmployerUser>> getAll();
	DataResult<EmployerUser> getById(int id);
	DataResult<EmployerUser> add(EmployerUser user);

}
