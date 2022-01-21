package com.btk.academia.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.SegmentService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.SegmentListDto;
import com.btk.academia.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btk.academia.rentACar.business.requests.segmentRequests.UpdateSegmentRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.SegmentDao;
import com.btk.academia.rentACar.entities.concretes.Segment;

@Service
public class SegmentManager implements SegmentService {
	//Depencies
	
	private ModelMapperService modelMapperService;
	private SegmentDao segmentDao;

	//Depency Injection
	@Autowired
	public SegmentManager(ModelMapperService modelMapperService, SegmentDao segmentDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.segmentDao = segmentDao;
	}

// finds a segment
	@Override
	public DataResult<SegmentListDto> FindById(int id) {
		return null;
	
	}

	@Override
	public Result add(CreateSegmentRequest createSegmentRequest) {
		Result result=BusinessRules.run(CheckIfSegmentNameAlreadyExists(createSegmentRequest.getSegmentName()));
				if(result!=null) {
					return result;
				}
				
				Segment segment=this.modelMapperService.forRequest().map(createSegmentRequest, Segment.class);
				this.segmentDao.save(segment);
				return new SuccessResult(Messages.segmentAdded);
		
	}
	//updates a new segment

	@Override
	public Result update(UpdateSegmentRequest updateSegmnetRequest) {
		Segment segment=this.modelMapperService.forRequest().map(updateSegmnetRequest, Segment.class);
		this.segmentDao.save(segment);
		return new SuccessResult("segment güncellendi");
	
	}
//Deletes  q new segment
	@Override
	public Result delete(int id) {
		if(segmentDao.existsById(id)) {
			segmentDao.deleteById(id);
			return new SuccessResult(Messages.segmentDeleted);
		} else return new ErrorResult("segment silindi");
		
	}
	//controls if there is a segment with that name 
	private Result CheckIfSegmentNameAlreadyExists(String SegmentName){
		if(segmentDao.findBySegmentName(SegmentName)!=null) {
			return new ErrorResult(Messages.segmentAlreadyExists);
		}
		return new SuccessResult();
		
	}
	
	
	

}
