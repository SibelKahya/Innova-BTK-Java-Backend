package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.SegmentListDto;
import com.btk.academia.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btk.academia.rentACar.business.requests.segmentRequests.UpdateSegmentRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface SegmentService {
	DataResult<SegmentListDto>FindById(int id);
	Result add(CreateSegmentRequest crreateSegmentRequest);
	Result update(UpdateSegmentRequest updateSegmnetRequest);
	Result delete(int id);

}
