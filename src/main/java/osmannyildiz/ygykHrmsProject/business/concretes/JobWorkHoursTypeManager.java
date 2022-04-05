package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobWorkHoursTypeService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobWorkHoursTypeDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobWorkHoursType;

@Service
public class JobWorkHoursTypeManager implements IJobWorkHoursTypeService {
	
	private IJobWorkHoursTypeDao jobWorkHoursTypeDao;

	@Autowired
	public JobWorkHoursTypeManager(IJobWorkHoursTypeDao jobWorkHoursTypeDao) {
		this.jobWorkHoursTypeDao = jobWorkHoursTypeDao;
	}

	@Override
	public DataResult<List<JobWorkHoursType>> getAll() {
		return new SuccessDataResult<List<JobWorkHoursType>>(jobWorkHoursTypeDao.findAll());
	}
	
	@Override
	public DataResult<List<JobWorkHoursType>> getAllWithSortingByName(boolean descending) {
		Sort.Direction direction = Sort.Direction.ASC;
		if (descending)
			direction = Sort.Direction.DESC;
		
		Sort sort = Sort.by(direction, "name");
		return new SuccessDataResult<List<JobWorkHoursType>>(
			jobWorkHoursTypeDao.findAll(sort), 
			"İş pozisyonları listelendi."
		);
	}

	@Override
	public DataResult<JobWorkHoursType> add(JobWorkHoursType jobWorkHoursType) {
		// TODO Validation
		
		boolean nameExists = existsWithName(jobWorkHoursType.getName()).getData();
		if (nameExists) {
			return new ErrorDataResult<JobWorkHoursType>("Bu isimde bir iş pozisyonu halihazırda mevcut.");
		}
		
		return new SuccessDataResult<JobWorkHoursType>(jobWorkHoursTypeDao.save(jobWorkHoursType), "İş pozisyonu eklendi.");
	}

	@Override
	public DataResult<Boolean> existsWithName(String name) {
		boolean exists = jobWorkHoursTypeDao.countByName(name) > 0;
		return new SuccessDataResult<Boolean>(exists);
	}
	
}
