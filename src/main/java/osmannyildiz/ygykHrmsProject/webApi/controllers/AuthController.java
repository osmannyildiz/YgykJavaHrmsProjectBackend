package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.business.abstracts.IAuthService;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private IAuthService authService;
	
	@Autowired
	public AuthController(IAuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/registerJobSeekerUser")
	public Result registerJobSeekerUser(@RequestBody JobSeekerUser jobSeekerUser) {
		return authService.registerJobSeekerUser(jobSeekerUser);
	}
	
	@PostMapping("/registerEmployerUser")
	public Result registerEmployerUser(@RequestBody EmployerUser employerUser) {
		return authService.registerEmployerUser(employerUser);
	}

	@PostMapping("/verifyUserByEmail")
	public Result verifyUserByEmail(@RequestParam int userId) {
		return authService.verifyUserByEmail(userId);
	}
	
	@PostMapping("/verifyEmployerUserBySystemUser")
	public Result verifyEmployerUserBySystemUser(@RequestParam int employerUserId) {
		return authService.verifyEmployerUserBySystemUser(employerUserId);
	}

}
