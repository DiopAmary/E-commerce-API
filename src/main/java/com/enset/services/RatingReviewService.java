package com.enset.services;

import org.springframework.stereotype.Service;

import com.enset.dto.RatingReviewDto;


public interface RatingReviewService {
	public void EnregistrerRatingReview(RatingReviewDto ratingReviewDto);

}
