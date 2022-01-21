package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.ColorService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.ColorDto;
import com.btk.academia.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btk.academia.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.ColorDao;
import com.btk.academia.rentACar.entities.concretes.Brand;
import com.btk.academia.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapperService modelMapperService) {
		this.colorDao=colorDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public DataResult<List<ColorDto>> getAll() {
		List<Color> colorList = this.colorDao.findAll();
		List<ColorDto> response = colorList.stream()
				.map(color->modelMapperService.forDto()
						.map(color, ColorDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorDto>>(response);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Result result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getName()));
		
		if(!result.isSuccess()) {
			return result;
		}

		Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
		color.setId(0);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorAdded);
	}
	
	private Result checkIfColorNameExists(String colorName) { //aynı isimde renk bir daha tekrar eklenemeyecek.
		Color color = colorDao.findByName(colorName);
		if (color != null) {
			return new ErrorResult(Messages.colorNameAlreadyExists);	
		}
		
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult(Messages.colorUpdated);
	}

}
