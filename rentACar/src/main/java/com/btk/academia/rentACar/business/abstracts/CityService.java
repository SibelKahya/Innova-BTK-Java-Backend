package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.dtos.CityDto;
import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CityService {

	Result add(CreateCityRequest createCityRequest);
	DataResult<List<CityDto>> getAll();	
}
