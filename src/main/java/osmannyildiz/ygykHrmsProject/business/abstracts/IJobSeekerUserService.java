package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

public interface IJobSeekerUserService {
	
	DataResult<List<JobSeekerUser>> getAll();

}
