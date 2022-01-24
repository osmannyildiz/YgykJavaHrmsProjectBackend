package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Result add(@RequestBody JobPosting jobPosting) {
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
