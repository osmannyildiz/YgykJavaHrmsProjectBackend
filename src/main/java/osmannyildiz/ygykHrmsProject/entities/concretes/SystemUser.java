package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="system_users")
@PrimaryKeyJoinColumn(name="user_id")
public class SystemUser extends User {
	
	public SystemUser() {}

	public SystemUser(int id, String email, String passwordHash, boolean emailVerified) {
		super(id, email, passwordHash, emailVerified);
	}
	
}
