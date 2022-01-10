package osmannyildiz.ygykHrmsProject.business.abstracts;

import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

public interface IAuthService {
	
	Result registerJobSeekerUser(JobSeekerUser jobSeekerUser);
	Result registerEmployerUser(EmployerUser employerUser);
	Result verifyUserByEmail(int userId);
	Result verifyEmployerUserBySystemUser(int employerUserId);

}
