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
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobPositionService;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobPositions")
public class JobPositionsController {
	
	private IJobPositionService jobPositionService;
	
	@Autowired
	public JobPositionsController(IJobPositionService jobPositionService) {
		this.jobPositionService = jobPositionService;
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
	public DataResult<List<JobPosition>> getAll() {
		return jobPositionService.getAll();
	}
	
	@GetMapping("/getAllWithSortingByName")
	public DataResult<List<JobPosition>> getAllWithSortingByName(@RequestParam boolean descending) {
		return jobPositionService.getAllWithSortingByName(descending);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid JobPosition jobPosition) {
		DataResult<JobPosition> result = jobPositionService.add(jobPosition);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}

}
