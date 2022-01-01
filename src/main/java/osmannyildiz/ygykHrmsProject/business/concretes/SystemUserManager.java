package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.ygykHrmsProject.business.abstracts.ISystemUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.ISystemUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.SystemUser;

@Service
public class SystemUserManager implements ISystemUserService {
	
	private ISystemUserDao systemUserDao;

	@Autowired
	public SystemUserManager(ISystemUserDao systemUserDao) {
		this.systemUserDao = systemUserDao;
	}

	@Override
	public List<SystemUser> getAll() {
		return systemUserDao.findAll();
	}
	
}
