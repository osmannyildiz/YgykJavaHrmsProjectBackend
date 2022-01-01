package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<JobPosition> getAll() {
		return jobPositionDao.findAll();
	}
	
}
