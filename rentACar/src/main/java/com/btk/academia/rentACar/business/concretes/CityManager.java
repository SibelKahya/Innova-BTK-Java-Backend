package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CityService;
import com.btk.academia.rentACar.business.dtos.CityDto;
import com.btk.academia.rentACar.business.dtos.ColorDto;
import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CityDao;
import com.btk.academia.rentACar.entities.concretes.City;
import com.btk.academia.rentACar.entities.concretes.Color;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao,ModelMapperService modelMapperService) {
		this.cityDao=cityDao;
		this.modelMapperService=modelMapperService;
	}
	
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run();
		
		if(!result.isSuccess()) {
			return result;
		}

		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		city.setId(0);
		this.cityDao.save(city);
		return new SuccessResult("Şehir eklendi");
	}

	@Override
	public DataResult<List<CityDto>> getAll() {
		List<City> cityList = this.cityDao.findAll();
		List<CityDto> response = cityList.stream()
				.map(city->modelMapperService.forDto()
						.map(city, CityDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityDto>>(response);
	}

}
