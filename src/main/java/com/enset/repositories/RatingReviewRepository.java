package com.enset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enset.entities.RatingReviewEntity;

public interface RatingReviewRepository extends JpaRepository<RatingReviewEntity, Long> {

}
