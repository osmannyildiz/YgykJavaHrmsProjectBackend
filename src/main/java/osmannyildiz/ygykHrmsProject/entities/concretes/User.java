package osmannyildiz.ygykHrmsProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	protected int id;

	@Column(name="email")
	protected String email;

	@Column(name="password_hash")
	protected String passwordHash;

	@Column(name="email_verified")
	protected boolean emailVerified;
	
	public User() {}

	public User(int id, String email, String passwordHash, boolean emailVerified) {
		this.id = id;
		this.email = email;
		this.passwordHash = passwordHash;
		this.emailVerified = emailVerified;
	}

}
