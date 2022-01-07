package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;

public interface IEmployerUserService {
	
	DataResult<List<EmployerUser>> getAll();

}
