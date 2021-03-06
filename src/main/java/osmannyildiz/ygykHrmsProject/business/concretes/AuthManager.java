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
import osmannyildiz.ygykHrmsProject.business.abstracts.IResumeService;
import osmannyildiz.ygykHrmsProject.business.abstracts.IUserService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IJobSeekerUserDao;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.IUserDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.Resume;
import osmannyildiz.ygykHrmsProject.entities.concretes.User;

@Service
public class AuthManager implements IAuthService {
	
	private IUserService userService;
	private IJobSeekerUserService jobSeekerUserService;
	private IEmployerUserService employerUserService;
	private IResumeService resumeService;
	private IMernisService mernisService;
	private IEmailService emailService;

	@Autowired
	public AuthManager(IUserService userService, IJobSeekerUserService jobSeekerUserService, IEmployerUserService employerUserService, IResumeService resumeService) {
		this.userService = userService;
		this.jobSeekerUserService = jobSeekerUserService;
		this.employerUserService = employerUserService;
		this.resumeService = resumeService;
		
		// TODO Make these work with the dependency injection
		this.mernisService = new MockMernisService();
		this.emailService = new MockEmailService();
	}

	@Override
	public Result registerJobSeekerUser(JobSeekerUser jobSeekerUser) {
		// TODO Validation
		
		boolean emailExists = userService.userWithEmailExists(jobSeekerUser.getEmail()).getData();
		if (emailExists) {
			return new ErrorResult("Bu e-posta adresiyle kaydolmu?? bir kullan??c?? halihaz??rda mevcut.");
		}
		
		boolean tcknExists = jobSeekerUserService.userWithTcknExists(jobSeekerUser.getTckn()).getData();
		if (tcknExists) {
			return new ErrorResult("Bu T.C. kimlik numaras??yla kaydolmu?? bir kullan??c?? halihaz??rda mevcut.");
		}
		
		DataResult<Boolean> mernisResult = mernisService.verifyTckn(jobSeekerUser.getTckn(), jobSeekerUser.getName(), jobSeekerUser.getSurname(), jobSeekerUser.getBirthYear());
		if (!mernisResult.isSuccess() || !mernisResult.getData()) {
			return new ErrorResult(mernisResult.getMessage());
		}
		
		DataResult<JobSeekerUser> addResult = jobSeekerUserService.add(jobSeekerUser);
		if (!addResult.isSuccess()) {
			return new ErrorResult(addResult.getMessage());
		}
		
		Resume resume = new Resume();
		resume.setJobSeekerUserId(addResult.getData().getId());
		resumeService.add(resume);
		
		emailService.send("noreply@kodlama.io", jobSeekerUser.getEmail(), "Hesap Do??rulama", "Hesab??n??z?? do??rulamak i??in ??u linke t??klay??n: <a href='example.com'>example.com</a>");
		return new SuccessResult("???? arayan kullan??c?? kayd?? ba??ar??yla ger??ekle??tirildi.");
	}

	@Override
	public Result registerEmployerUser(EmployerUser employerUser) {
		// TODO Validation
		
		boolean emailExists = userService.userWithEmailExists(employerUser.getEmail()).getData();
		if (emailExists) {
			return new ErrorResult("Bu e-posta adresiyle kaydolmu?? bir kullan??c?? halihaz??rda mevcut.");
		}
		
		boolean emailBelongsToDomain = checkIfEmailBelongsToDomain(employerUser.getEmail(), employerUser.getWebsite());
		if (!emailBelongsToDomain) {
			return new ErrorResult("L??tfen kendi websitenizle ayn?? alan ad??na ait bir e-posta adresi girin.");
		}
		
		DataResult<EmployerUser> addResult = employerUserService.add(employerUser);
		if (!addResult.isSuccess()) {
			return new ErrorResult(addResult.getMessage());
		}
		
		emailService.send("noreply@kodlama.io", employerUser.getEmail(), "Hesap Do??rulama", "Hesab??n??z?? do??rulamak i??in ??u linke t??klay??n: <a href='example.com'>example.com</a>");
		return new SuccessResult("????veren kullan??c?? kayd?? ba??ar??yla ger??ekle??tirildi.");
	}
	
	@Override
	public Result verifyUserByEmail(int userId) {
		// TODO Verify by verification token instead of userId
		User user = userService.getById(userId).getData();
		user.setEmailVerified(true);
		userService.update(user);
		return new SuccessResult("Kullan??c?? e-posta ile do??ruland??.");
	}
	
	@Override
	public Result verifyEmployerUserBySystemUser(int employerUserId) {
		// TODO
		EmployerUser user = employerUserService.getById(employerUserId).getData();
		user.setSystemUserVerified(true);
		userService.update(user);
		return new SuccessResult("Kullan??c?? sistem kullan??c??s?? ile do??ruland??.");
	}
	
	private boolean checkIfEmailBelongsToDomain(String email, String domain) {
		// TODO
		return true;
	}

}
