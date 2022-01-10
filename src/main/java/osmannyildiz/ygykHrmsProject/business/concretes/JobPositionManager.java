package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobPositionService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobPositionDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements IJobPositionService {
	
	private IJobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(IJobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll());
	}

	@Override
	public DataResult<JobPosition> add(JobPosition jobPosition) {
		// TODO Validation
		
		boolean nameExists = jobPositionWithNameExists(jobPosition.getName()).getData();
		if (nameExists) {
			return new ErrorDataResult("Bu isimde bir iş pozisyonu halihazırda mevcut.");
		}
		
		return new SuccessDataResult<JobPosition>(jobPositionDao.save(jobPosition), "İş pozisyonu eklendi.");
	}

	@Override
	public DataResult<Boolean> jobPositionWithNameExists(String name) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id");
		JobPosition exampleJobPosition = new JobPosition();
		exampleJobPosition.setName(name);
		Example<JobPosition> example = Example.of(exampleJobPosition, matcher);
		boolean exists = jobPositionDao.exists(example);
		return new SuccessDataResult<Boolean>(exists);
	}
	
}
