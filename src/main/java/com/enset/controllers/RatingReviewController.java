package com.enset.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enset.dto.ProduitDto;
import com.enset.dto.RatingReviewDto;
import com.enset.dto.UserDto;
import com.enset.entities.RatingReviewEntity;
import com.enset.requests.RatingReviewRequest;
import com.enset.services.ProduitService;
import com.enset.services.RatingReviewService;
import com.enset.services.UserService;

@RestController
public class RatingReviewController {
	@Autowired 
	RatingReviewService ratingReviewService;
	@Autowired
	UserService userService;
	@Autowired
	ProduitService produitService;
	
	 @PostMapping("/detailProduit/{userId,produitId}")
	 public String saveRatingReview(@PathVariable String userId,@PathVariable long produitId,RatingReviewRequest ratingReviewRequest) {
		 ModelMapper modelMapper= new ModelMapper();
		 ProduitDto produitDto= produitService.getProduitById(produitId);
		 UserDto userDto= userService.getUserByUserId(userId);
		 
		 ratingReviewRequest.setProduitDto(produitDto);
		 ratingReviewRequest.setUserDto(userDto);
		 RatingReviewDto ratingReviewDto= modelMapper.map(ratingReviewRequest, RatingReviewDto.class);
		 ratingReviewService.EnregistrerRatingReview(ratingReviewDto);
		 return "detailProduit";
		 
		 
		 
		 
	 }

}
