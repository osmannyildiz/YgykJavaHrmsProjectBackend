package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosition;

public interface IJobPositionDao extends JpaRepository<JobPosition, Integer> {
	
	int countByName(String name);

}
