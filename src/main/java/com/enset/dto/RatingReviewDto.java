package com.enset.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingReviewDto implements Serializable {
	private static final long serialVersionUID = -3684097076852721160L;
	private long id;
	private String commentaire;
	private int rating;
	private Date dateCommentaire;
	ProduitDto produitDto;
	UserDto userDto;
	
}
