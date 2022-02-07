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
import osmannyildiz.ygykHrmsProject.business.abstracts.ICityService;
import osmannyildiz.ygykHrmsProject.entities.concretes.City;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	
	private ICityService cityService;
	
	@Autowired
	public CitiesController(ICityService cityService) {
		this.cityService = cityService;
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

	@GetMapping("/getAllWithSortingByName")
	public DataResult<List<City>> getAllWithSortingByName(@RequestParam boolean descending) {
		return cityService.getAllWithSortingByName(descending);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid City city) {
		DataResult<City> result = cityService.add(city);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}

}
