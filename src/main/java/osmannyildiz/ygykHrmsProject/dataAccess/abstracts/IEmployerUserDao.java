package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;

public interface IEmployerUserDao extends JpaRepository<EmployerUser, Integer> {

}
