package osmannyildiz.ygykHrmsProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import osmannyildiz.coreProject.utilities.results.DataResult;
import osmannyildiz.coreProject.utilities.results.SuccessDataResult;
import osmannyildiz.ygykHrmsProject.business.abstracts.ICityService;
import osmannyildiz.ygykHrmsProject.dataAccess.abstracts.ICityDao;
import osmannyildiz.ygykHrmsProject.entities.concretes.City;

@Service
public class CityManager implements ICityService {
	
	private ICityDao cityDao;

	@Autowired
	public CityManager(ICityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAllWithSortingByName(boolean descending) {
		Sort.Direction direction = Sort.Direction.ASC;
		if (descending)
			direction = Sort.Direction.DESC;
		
		Sort sort = Sort.by(direction, "name");
		return new SuccessDataResult<List<City>>(
			cityDao.findAll(sort), 
			"Şehirler listelendi."
		);
	}

	@Override
	public DataResult<City> add(City city) {
		return new SuccessDataResult<City>(cityDao.save(city), "Şehir eklendi.");
	}

}
