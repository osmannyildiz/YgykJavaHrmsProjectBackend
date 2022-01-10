package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.IEmployerUserService;
import osmannyildiz.ygykHrmsProject.entities.concretes.EmployerUser;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	
	private IEmployerUserService employerUserService;
	
	@Autowired
	public EmployersController(IEmployerUserService employerUserService) {
		this.employerUserService = employerUserService;
	}

	@GetMapping("/getAll")
	public DataResult<List<EmployerUser>> getAll() {
		// TODO Don't expose all the data, use a DTO!!!
		return employerUserService.getAll();
	}

}
