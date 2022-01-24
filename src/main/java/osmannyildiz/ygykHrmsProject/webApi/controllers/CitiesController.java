package osmannyildiz.ygykHrmsProject.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import osmannyildiz.coreProject.utilities.results.DataResult;
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

	@GetMapping("/getAllWithSortingByName")
	public DataResult<List<City>> getAllWithSortingByName(@RequestParam boolean descending) {
		return cityService.getAllWithSortingByName(descending);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody City city) {
		DataResult<City> result = cityService.add(city);
		if (!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		return new SuccessResult(result.getMessage());
	}

}
