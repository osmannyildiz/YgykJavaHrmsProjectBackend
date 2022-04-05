package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.ygykHrmsProject.business.abstracts.IAuthService;
import osmannyildiz.ygykHrmsProject.business.constants.Messages;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<String, String>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(errors, Messages.fixValidationErrors);
	}
	
	@PostMapping("/registerJobSeekerUser")
	public Result registerJobSeekerUser(@RequestBody @Valid JobSeekerUser jobSeekerUser) {
		return authService.registerJobSeekerUser(jobSeekerUser);
	}
	
	@PostMapping("/registerEmployerUser")
	public Result registerEmployerUser(@RequestBody @Valid EmployerUser employerUser) {
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
