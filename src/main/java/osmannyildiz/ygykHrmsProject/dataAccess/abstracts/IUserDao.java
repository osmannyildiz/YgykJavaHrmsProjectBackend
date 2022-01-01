package osmannyildiz.ygykHrmsProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import osmannyildiz.ygykHrmsProject.entities.concretes.User;

public interface IUserDao extends JpaRepository<User, Integer> {

}
