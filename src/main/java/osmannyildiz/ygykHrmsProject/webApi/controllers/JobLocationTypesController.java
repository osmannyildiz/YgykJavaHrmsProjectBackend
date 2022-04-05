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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.ErrorDataResult;
import osmannyildiz.coreProject.utilities.results.ErrorResult;
import osmannyildiz.coreProject.utilities.results.Result;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobLocationTypeService;
import osmannyildiz.ygykHrmsProject.business.constants.Messages;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobLocationType;

@RestController
@RequestMapping("/api/jobLocationTypes")
public class JobLocationTypesController {
	
	private IJobLocationTypeService jobLocationTypeService;
	
	@Autowired
	public JobLocationTypesController(IJobLocationTypeService jobLocationTypeService) {
		this.jobLocationTypeService = jobLocationTypeService;
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
	public DataResult<List<JobLocationType>> getAll() {
		return jobLocationTypeService.getAll();
	}
	
	@GetMapping("/getAllWithSortingByName")
	public DataResult<List<JobLocationType>> getAllWithSortingByName(@RequestParam boolean descending) {
		return jobLocationTypeService.getAllWithSortingByName(descending);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid JobLocationType jobLocationType) {
		DataResult<JobLocationType> result = jobLocationTypeService.add(jobLocationType);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}

}
