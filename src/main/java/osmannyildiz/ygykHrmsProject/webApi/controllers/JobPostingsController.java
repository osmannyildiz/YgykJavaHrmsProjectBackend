package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.ArrayList;
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
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.coreProject.utilities.results.SuccessResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IJobPostingService;
import osmannyildiz.ygykHrmsProject.entities.concretes.JobPosting;
import osmannyildiz.ygykHrmsProject.entities.dtos.JobPostingViewDto;

@RestController
@RequestMapping("/api/jobPostings")
public class JobPostingsController {
	
	private IJobPostingService jobPostingService;
	
	@Autowired
	public JobPostingsController(IJobPostingService jobPostingService) {
		this.jobPostingService = jobPostingService;
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
	
	@GetMapping("/getAllActive")
	public DataResult<List<JobPostingViewDto>> getAllActive() {
		DataResult<List<JobPosting>> result = jobPostingService.getAllActive();
		if (!result.isSuccess()) {
			return new ErrorDataResult<List<JobPostingViewDto>>(result.getMessage());
		}
		
		List<JobPostingViewDto> data = convertJobPostingsToJobPostingViewDtos(result.getData());
		return new SuccessDataResult<List<JobPostingViewDto>>(data, result.getMessage());
	}

	@GetMapping("/getAllActiveWithSortingByApplicationDeadline")
	public DataResult<List<JobPostingViewDto>> getAllActiveWithSortingByApplicationDeadline(@RequestParam boolean descending) {
		DataResult<List<JobPosting>> result = jobPostingService.getAllActiveWithSortingByApplicationDeadline(descending);
		if (!result.isSuccess()) {
			return new ErrorDataResult<List<JobPostingViewDto>>(result.getMessage());
		}
		
		List<JobPostingViewDto> data = convertJobPostingsToJobPostingViewDtos(result.getData());
		return new SuccessDataResult<List<JobPostingViewDto>>(data, result.getMessage());
	}
	
	@GetMapping("/getAllActiveByEmployerId")
	public DataResult<List<JobPostingViewDto>> getAllActiveByEmployerId(@RequestParam int employerId) {
		DataResult<List<JobPosting>> result = jobPostingService.getAllActiveByEmployerId(employerId);
		if (!result.isSuccess()) {
			return new ErrorDataResult<List<JobPostingViewDto>>(result.getMessage());
		}
		
		List<JobPostingViewDto> data = convertJobPostingsToJobPostingViewDtos(result.getData());
		return new SuccessDataResult<List<JobPostingViewDto>>(data, result.getMessage());
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid JobPosting jobPosting) {
		DataResult<JobPosting> result = jobPostingService.add(jobPosting);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}
	
	@GetMapping("/activate")
	public Result activate(@RequestParam int id) {
		DataResult<JobPosting> entityResult = jobPostingService.getById(id);
		if (!entityResult.isSuccess()) {
			return new ErrorResult(entityResult.getMessage());
		}
		
		JobPosting jobPosting = entityResult.getData();
		jobPosting.setActive(true);
		DataResult<JobPosting> updateResult = jobPostingService.update(jobPosting);
		if (!updateResult.isSuccess()) {
			return new ErrorResult(updateResult.getMessage());
		}
		
		return new SuccessResult("İş ilanı aktifleştirildi.");
	}
	
	@GetMapping("/deactivate")
	public Result deactivate(@RequestParam int id) {
		DataResult<JobPosting> entityResult = jobPostingService.getById(id);
		if (!entityResult.isSuccess()) {
			return new ErrorResult(entityResult.getMessage());
		}
		
		JobPosting jobPosting = entityResult.getData();
		jobPosting.setActive(false);
		DataResult<JobPosting> updateResult = jobPostingService.update(jobPosting);
		if (!updateResult.isSuccess()) {
			return new ErrorResult(updateResult.getMessage());
		}
		
		return new SuccessResult("İş ilanı pasifleştirildi.");
	}
	
	private List<JobPostingViewDto> convertJobPostingsToJobPostingViewDtos(List<JobPosting> jobPostings) {
		List<JobPostingViewDto> jobPostingViewDtos = new ArrayList<JobPostingViewDto>();
		for (JobPosting jobPosting : jobPostings) {
			jobPostingViewDtos.add(new JobPostingViewDto(jobPosting));
		}
		return jobPostingViewDtos;
	}

}
