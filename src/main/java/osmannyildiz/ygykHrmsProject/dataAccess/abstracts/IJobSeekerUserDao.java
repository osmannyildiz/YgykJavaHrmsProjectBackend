package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

public interface IJobSeekerUserDao extends JpaRepository<JobSeekerUser, Integer> {

}
