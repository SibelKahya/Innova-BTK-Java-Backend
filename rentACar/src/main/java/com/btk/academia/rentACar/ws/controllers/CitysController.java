package com.btk.academia.rentACar.ws.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.btk.academia.rentACar.business.abstracts.CityService;
import com.btk.academia.rentACar.business.dtos.CityDto;
import com.btk.academia.rentACar.business.dtos.ColorDto;
import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/citys")
@CrossOrigin
public class CitysController {
	
	private CityService cityService;

	public CitysController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateCityRequest createCityRequest){
		return this.cityService.add(createCityRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<CityDto>> getAll(){
		return this.cityService.getAll();
	}

}
