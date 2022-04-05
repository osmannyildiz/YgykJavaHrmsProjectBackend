package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobLocationType;

public interface IJobLocationTypeDao extends JpaRepository<JobLocationType, Integer> {
	
	int countByName(String name);

}
