package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobSeekerUserService;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobSeekerUser;

@RestController
@RequestMapping("/api/jobSeekers")
public class JobSeekersController {
	
	private IJobSeekerUserService jobSeekerUserService;
	
	@Autowired
	public JobSeekersController(IJobSeekerUserService jobSeekerUserService) {
		this.jobSeekerUserService = jobSeekerUserService;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<String, String>();
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(errors, "Lütfen doğrulama hatalarını düzeltip tekrar deneyin.");
	}

	@GetMapping("/getAll")
	public DataResult<List<JobSeekerUser>> getAll() {
		// TODO Don't expose all the data, use a DTO!!!
		return jobSeekerUserService.getAll();
	}

}
