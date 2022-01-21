package com.btk.academia.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.SegmentService;
import com.btk.academia.rentACar.business.requests.segmentRequests.CreateSegmentRequest;
import com.btk.academia.rentACar.business.requests.segmentRequests.UpdateSegmentRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {
	private SegmentService segmentService;
	@Autowired
	public SegmentController(SegmentService segmentService) {
		super();
		this.segmentService = segmentService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateSegmentRequest createSegmentRequest) {
		return this.segmentService.add(createSegmentRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateSegmentRequest createSegmentRequest) {
		return this.segmentService.update(createSegmentRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(int id) {
		return this.segmentService.delete(id);
	}
	
	

}
