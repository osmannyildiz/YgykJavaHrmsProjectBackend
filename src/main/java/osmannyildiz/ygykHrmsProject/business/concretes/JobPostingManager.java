package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobPostingService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobPostingDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements IJobPostingService {
	
	private IJobPostingDao jobPostingDao;

	@Autowired
	public JobPostingManager(IJobPostingDao jobPostingDao) {
		this.jobPostingDao = jobPostingDao;
	}
	
	@Override
	public DataResult<List<JobPosting>> getAll() {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.findAll(), "İş ilanları listelendi.");
	}

	@Override
	public DataResult<List<JobPosting>> getAllWithSortingByApplicationDeadline(boolean descending) {
		Sort.Direction direction = Sort.Direction.ASC;
		if (descending)
			direction = Sort.Direction.DESC;
		
		Sort sort = Sort.by(direction, "applicationDeadline");
		return new SuccessDataResult<List<JobPosting>>(
			jobPostingDao.findAll(sort), 
			"İş ilanları listelendi."
		);
	}

	@Override
	public DataResult<List<JobPosting>> getAllByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByEmployerId(employerId), "İş ilanları listelendi.");
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllActive() {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByActive(true), "İş ilanları listelendi.");
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveWithSortingByApplicationDeadline(boolean descending) {
		List<JobPosting> data;
		if (descending) {
			data = jobPostingDao.getByActiveOrderByApplicationDeadlineDesc(true);
		} else {
			data = jobPostingDao.getByActiveOrderByApplicationDeadlineAsc(true);
		}
		
		return new SuccessDataResult<List<JobPosting>>(data, "İş ilanları listelendi.");
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByActiveAndEmployerId(true, employerId), "İş ilanları listelendi.");
	}

	@Override
	public DataResult<JobPosting> getById(int id) {
		return new SuccessDataResult<JobPosting>(jobPostingDao.getById(id));
	}

	@Override
	public DataResult<JobPosting> add(JobPosting jobPosting) {
		return new SuccessDataResult<JobPosting>(jobPostingDao.save(jobPosting), "İş ilanı eklendi.");
	}
	
	@Override
	public DataResult<JobPosting> update(JobPosting jobPosting) {
		return new SuccessDataResult<JobPosting>(jobPostingDao.save(jobPosting), "İş ilanı güncellendi.");
	}

}
