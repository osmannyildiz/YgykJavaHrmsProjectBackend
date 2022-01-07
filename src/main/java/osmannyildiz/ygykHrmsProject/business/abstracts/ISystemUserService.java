package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.SystemUser;

public interface ISystemUserService {
	
	DataResult<List<SystemUser>> getAll();

}
