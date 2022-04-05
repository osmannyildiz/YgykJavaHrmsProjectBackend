package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobWorkHoursType;

public interface IJobWorkHoursTypeDao extends JpaRepository<JobWorkHoursType, Integer> {
	
	int countByName(String name);

}
