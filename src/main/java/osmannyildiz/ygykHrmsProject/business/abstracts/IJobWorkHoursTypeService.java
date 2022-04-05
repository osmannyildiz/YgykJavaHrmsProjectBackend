package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobWorkHoursType;

public interface IJobWorkHoursTypeService {
	
	DataResult<List<JobWorkHoursType>> getAll();
	DataResult<List<JobWorkHoursType>> getAllWithSortingByName(boolean descending);
	DataResult<JobWorkHoursType> add(JobWorkHoursType jobWorkHoursType);
	DataResult<Boolean> existsWithName(String name);

}
