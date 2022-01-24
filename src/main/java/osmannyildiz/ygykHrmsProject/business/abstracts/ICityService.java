package osmannyildiz.ygykHrmsProject.business.abstracts;

import java.util.List;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.ygykHrmsProject.entities.concretes.City;

public interface ICityService {
	
	DataResult<List<City>> getAllWithSortingByName(boolean descending);
	DataResult<City> add(City city);

}
