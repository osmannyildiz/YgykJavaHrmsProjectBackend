package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobLocationType;

public interface IJobLocationTypeService {
	
	DataResult<List<JobLocationType>> getAll();
	DataResult<List<JobLocationType>> getAllWithSortingByName(boolean descending);
	DataResult<JobLocationType> add(JobLocationType jobLocationType);
	DataResult<Boolean> existsWithName(String name);

}
