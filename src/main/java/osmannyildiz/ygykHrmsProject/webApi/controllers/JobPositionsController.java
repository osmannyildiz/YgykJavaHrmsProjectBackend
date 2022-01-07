package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
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

	@GetMapping("/getAll")
	public DataResult<List<JobPosition>> getAll() {
		return jobPositionService.getAll();
	}

}
