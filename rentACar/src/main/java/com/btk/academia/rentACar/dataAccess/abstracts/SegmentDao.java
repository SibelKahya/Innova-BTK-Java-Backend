package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.Segment;

public interface SegmentDao extends JpaRepository<Segment, Integer>{
	
	Segment findBySegmentName(String segmentName);

}
