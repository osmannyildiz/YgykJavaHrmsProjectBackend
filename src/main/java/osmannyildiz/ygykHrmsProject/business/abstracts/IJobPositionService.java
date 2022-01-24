package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosition;

public interface IJobPositionService {
	
	DataResult<List<JobPosition>> getAll();
	DataResult<List<JobPosition>> getAllWithSortingByName(boolean descending);
	DataResult<JobPosition> add(JobPosition jobPosition);
	DataResult<Boolean> jobPositionWithNameExists(String name);

}
