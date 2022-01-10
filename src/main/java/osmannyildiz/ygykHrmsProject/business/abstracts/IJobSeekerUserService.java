package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

public interface IJobSeekerUserService {
	
	DataResult<List<JobSeekerUser>> getAll();
	DataResult<JobSeekerUser> add(JobSeekerUser user);
	DataResult<JobSeekerUser> update(JobSeekerUser user);
	DataResult<Boolean> userWithTcknExists(String tckn);

}
