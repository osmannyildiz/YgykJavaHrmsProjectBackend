package osmannyildiz.ygykHrmsProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.business.abstracts.IEmailService;
import osmannyildiz.coreProject.business.abstracts.IMernisService;
import osmannyildiz.coreProject.business.concretes.MernisService;
import osmannyildiz.coreProject.business.concretes.MockEmailService;
import osmannyildiz.coreProject.business.concretes.MockMernisService;
import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IAuthService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IEmployerUserService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobSeekerUserService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobSeekerUserDao;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

@Service
public class AuthManager implements IAuthService {
	
	private IUserService userService;
	private IJobSeekerUserService jobSeekerUserService;
	private IEmployerUserService employerUserService;
	private IMernisService mernisService;
	private IEmailService emailService;

	@Autowired
	public AuthManager(IUserService userService, IJobSeekerUserService jobSeekerUserService, IEmployerUserService employerUserService) {
		this.userService = userService;
		this.jobSeekerUserService = jobSeekerUserService;
		this.employerUserService = employerUserService;
		
		// TODO Make these work with the dependency injection
		this.mernisService = new MockMernisService();
		this.emailService = new MockEmailService();
	}

	@Override
	public Result registerJobSeekerUser(JobSeekerUser jobSeekerUser) {
		// TODO Validation
		
		boolean emailExists = userService.userWithEmailExists(jobSeekerUser.getEmail()).getData();
		if (emailExists) {
			return new ErrorResult("Bu e-posta adresiyle kaydolmuş bir kullanıcı halihazırda mevcut.");
		}
		
		boolean tcknExists = jobSeekerUserService.userWithTcknExists(jobSeekerUser.getTckn()).getData();
		if (tcknExists) {
			return new ErrorResult("Bu T.C. kimlik numarasıyla kaydolmuş bir kullanıcı halihazırda mevcut.");
		}
		
		DataResult<Boolean> mernisResult = mernisService.verifyTckn(jobSeekerUser.getTckn(), jobSeekerUser.getName(), jobSeekerUser.getSurname(), jobSeekerUser.getBirthYear());
		if (!mernisResult.isSuccess() || !mernisResult.getData()) {
			return new ErrorResult(mernisResult.getMessage());
		}
		
		DataResult<JobSeekerUser> addResult = jobSeekerUserService.add(jobSeekerUser);
		if (!addResult.isSuccess()) {
			return new ErrorResult(addResult.getMessage());
		}
		
		emailService.send("noreply@kodlama.io", jobSeekerUser.getEmail(), "Hesap Doğrulama", "Hesabınızı doğrulamak için şu linke tıklayın: <a href='example.com'>example.com</a>");
		return new SuccessResult("İş arayan kullanıcı kaydı başarıyla gerçekleştirildi.");
	}

	@Override
	public Result registerEmployerUser(EmployerUser employerUser) {
		// TODO Validation
		
		boolean emailExists = userService.userWithEmailExists(employerUser.getEmail()).getData();
		if (emailExists) {
			return new ErrorResult("Bu e-posta adresiyle kaydolmuş bir kullanıcı halihazırda mevcut.");
		}
		
		boolean emailBelongsToDomain = checkIfEmailBelongsToDomain(employerUser.getEmail(), employerUser.getWebsite());
		if (!emailBelongsToDomain) {
			return new ErrorResult("Lütfen kendi websitenizle aynı alan adına ait bir e-posta adresi girin.");
		}
		
		DataResult<EmployerUser> addResult = employerUserService.add(employerUser);
		if (!addResult.isSuccess()) {
			return new ErrorResult(addResult.getMessage());
		}
		
		emailService.send("noreply@kodlama.io", employerUser.getEmail(), "Hesap Doğrulama", "Hesabınızı doğrulamak için şu linke tıklayın: <a href='example.com'>example.com</a>");
		return new SuccessResult("İşveren kullanıcı kaydı başarıyla gerçekleştirildi.");
	}
	
	@Override
	public Result verifyUserByEmail(int userId) {
		// TODO Verify by verification token instead of userId
		User user = userService.getById(userId).getData();
		user.setEmailVerified(true);
		userService.update(user);
		return new SuccessResult("Kullanıcı e-posta ile doğrulandı.");
	}
	
	@Override
	public Result verifyEmployerUserBySystemUser(int employerUserId) {
		// TODO
		EmployerUser user = employerUserService.getById(employerUserId).getData();
		user.setSystemUserVerified(true);
		userService.update(user);
		return new SuccessResult("Kullanıcı sistem kullanıcısı ile doğrulandı.");
	}
	
	private boolean checkIfEmailBelongsToDomain(String email, String domain) {
		// TODO
		return true;
	}

}
