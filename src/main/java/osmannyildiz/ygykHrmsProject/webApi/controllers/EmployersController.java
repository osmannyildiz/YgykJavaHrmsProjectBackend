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
import osmannyildiz.ygykHrmsProject.business.abstracts.IEmployerUserService;
import osmannyildiz.ygykHrmsProject.business.constants.Messages;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	
	private IEmployerUserService employerUserService;
	
	@Autowired
	public EmployersController(IEmployerUserService employerUserService) {
		this.employerUserService = employerUserService;
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

	@GetMapping("/getAll")
	public DataResult<List<EmployerUser>> getAll() {
		// TODO Don't expose all the data, use a DTO!!!
		return employerUserService.getAll();
	}

}
