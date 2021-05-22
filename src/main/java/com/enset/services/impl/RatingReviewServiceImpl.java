package com.enset.services.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enset.dto.RatingReviewDto;
import com.enset.entities.RatingReviewEntity;
import com.enset.repositories.RatingReviewRepository;
import com.enset.services.RatingReviewService;


@Service
@Transactional
public class RatingReviewServiceImpl implements RatingReviewService {
	
	 @Autowired
	 private RatingReviewRepository ratingReviewRepository;
	 
	@Override
	public void EnregistrerRatingReview(RatingReviewDto ratingReviewDto) {
		ModelMapper modelMapper = new ModelMapper();
		RatingReviewEntity ratingReviewEntity = modelMapper.map(ratingReviewDto, RatingReviewEntity.class);	
		ratingReviewRepository.save(ratingReviewEntity);	
	}
}
