package com.enset.requests;

import java.io.Serializable;
import java.sql.Date;

import com.enset.dto.ProduitDto;
import com.enset.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RatingReviewRequest {
	
		private String commentaire;
		private int rating;
		private Date dateCommentaire;
		ProduitDto produitDto;
		UserDto userDto;
		
	}


