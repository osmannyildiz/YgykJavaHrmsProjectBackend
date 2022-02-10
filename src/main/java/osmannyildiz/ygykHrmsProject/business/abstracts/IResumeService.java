package osmannyildiz.ygykHrmsProject.business.abstracts;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.Resume;

public interface IResumeService {
	
	DataResult<Resume> getById(int id);
	
	DataResult<Resume> add(Resume resume);
	DataResult<Resume> update(Resume resume);

}
