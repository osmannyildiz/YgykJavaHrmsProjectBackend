package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;

public interface IJobPostingService {
	
	DataResult<List<JobPosting>> getAll();
	DataResult<List<JobPosting>> getAllWithSortingByApplicationDeadline(boolean descending);
	DataResult<List<JobPosting>> getAllByEmployerId(int employerId);
	DataResult<List<JobPosting>> getAllActive();
	DataResult<List<JobPosting>> getAllActiveWithSortingByApplicationDeadline(boolean descending);
	DataResult<List<JobPosting>> getAllActiveByEmployerId(int employerId);
	DataResult<JobPosting> getById(int id);
	DataResult<JobPosting> add(JobPosting jobPosting);
	DataResult<JobPosting> update(JobPosting jobPosting);

}
