package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.btk.academia.rentACar.business.abstracts.CarMaintanceService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.dtos.CarMaintanceDto;
import com.btk.academia.rentACar.business.requests.carMaintanceRequest.CreateCarMaintanceRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CarMaintanceDao;
import com.btk.academia.rentACar.entities.concretes.CarMaintanance;
@Service
public class CarMaintananceManager implements CarMaintanceService{
	
	private CarMaintanceDao carMaintanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	@Autowired
	public CarMaintananceManager(CarMaintanceDao carMaintanceDao,
			ModelMapperService modelMapperService,
			RentalService rentalService) {
		this.carMaintanceDao = carMaintanceDao;
		this.modelMapperService=modelMapperService;
		this.rentalService=rentalService;
	}

	@Override
	public Result add(CreateCarMaintanceRequest createCarMaintanceRequest) {
		Result result = BusinessRules.run(
				checkCarRental(createCarMaintanceRequest.getCarId())
				);
		
		if(!result.isSuccess()) {
			return result;
		}

		CarMaintanance carMaintance = modelMapperService.forRequest().map(createCarMaintanceRequest, CarMaintanance.class);
		carMaintance.setId(0);
		this.carMaintanceDao.save(carMaintance);
		return new SuccessResult("ara?? bak??ma g??nderildi");
	}
	
	@Override
	public DataResult<List<CarMaintanceDto>> getAll() {
		List<CarMaintanance> carMaintances = this.carMaintanceDao.findAll();
		List<CarMaintanceDto> response = carMaintances.stream()
				.map(carMaintance -> modelMapperService.forDto().map(carMaintance, CarMaintanceDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarMaintanceDto>>(response);
	}
	
	@Override
	public DataResult<CarMaintanance> getByCarId(Integer id) {
		CarMaintanance carMaintanance = this.carMaintanceDao.findByCarId(id);
		return new SuccessDataResult<CarMaintanance>(carMaintanance);
	}
	
	//Araba bak??mdan d??nmediyse tekrar bak??ma g??nderilemez
	private Result checkCarMaintanance(Integer carId) {
        CarMaintanance carMaintanance = this.carMaintanceDao.findByCarId(carId);
        
		if (carMaintanance!=null && carMaintanance.getMaintenanceEnd()==null) {
			return new ErrorResult("araba bak??mda");
		}

		return new SuccessResult();
	}
	
	//Araba kiraland??ysa bak??ma g??nderilemez
	//rental tablosunda, carId kontrol et ve kiralan??p kiralanmad??????n?? ????ren
	private Result checkCarRental(Integer carId) {
        
		if (rentalService.getByCarId(carId)!=null) {
			return new ErrorResult("bu araba kiraland?? bak??ma g??nderilemez");
		}

		return new SuccessResult();
	}
	


}
