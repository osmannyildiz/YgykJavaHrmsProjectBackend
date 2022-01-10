package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
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

	@GetMapping("/getAll")
	public DataResult<List<JobSeekerUser>> getAll() {
		// TODO Don't expose all the data, use a DTO!!!
		return jobSeekerUserService.getAll();
	}

}
