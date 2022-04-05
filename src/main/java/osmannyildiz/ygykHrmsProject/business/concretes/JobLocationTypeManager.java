package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobLocationTypeService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobLocationTypeDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobLocationType;

@Service
public class JobLocationTypeManager implements IJobLocationTypeService {
	
	private IJobLocationTypeDao jobLocationTypeDao;

	@Autowired
	public JobLocationTypeManager(IJobLocationTypeDao jobLocationTypeDao) {
		this.jobLocationTypeDao = jobLocationTypeDao;
	}

	@Override
	public DataResult<List<JobLocationType>> getAll() {
		return new SuccessDataResult<List<JobLocationType>>(jobLocationTypeDao.findAll());
	}
	
	@Override
	public DataResult<List<JobLocationType>> getAllWithSortingByName(boolean descending) {
		Sort.Direction direction = Sort.Direction.ASC;
		if (descending)
			direction = Sort.Direction.DESC;
		
		Sort sort = Sort.by(direction, "name");
		return new SuccessDataResult<List<JobLocationType>>(
			jobLocationTypeDao.findAll(sort), 
			"İş pozisyonları listelendi."
		);
	}

	@Override
	public DataResult<JobLocationType> add(JobLocationType jobLocationType) {
		// TODO Validation
		
		boolean nameExists = existsWithName(jobLocationType.getName()).getData();
		if (nameExists) {
			return new ErrorDataResult<JobLocationType>("Bu isimde bir iş pozisyonu halihazırda mevcut.");
		}
		
		return new SuccessDataResult<JobLocationType>(jobLocationTypeDao.save(jobLocationType), "İş pozisyonu eklendi.");
	}

	@Override
	public DataResult<Boolean> existsWithName(String name) {
		boolean exists = jobLocationTypeDao.countByName(name) > 0;
		return new SuccessDataResult<Boolean>(exists);
	}
	
}
