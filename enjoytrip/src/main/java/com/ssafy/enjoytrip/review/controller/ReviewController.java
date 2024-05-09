package com.ssafy.enjoytrip.review.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.domain.Member;
import com.ssafy.enjoytrip.member.model.LoginRequest;
import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.review.domain.Review;
import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;
import com.ssafy.enjoytrip.review.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService =  reviewService;
	}
	
	@GetMapping("/{hotplaceId}")
	public ResponseEntity<?> getAllReview(@PathVariable String hotPlaceId) throws Exception {
		try {
			List<ReviewDto> tmpReviews = reviewService.getAllReview(hotPlaceId);
			List<Review> reviews = new ArrayList<Review>();
			for(ReviewDto review : tmpReviews) {
				reviews.add(new Review(review));
			}
			return ResponseEntity.ok(reviews);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMemberReview(@PathVariable String hotPlaceId) throws Exception {
		try {
			List<ReviewDto> tmpReviews = reviewService.getMemberReview(hotPlaceId);
			List<Review> reviews = new ArrayList<Review>();
			for(ReviewDto review : tmpReviews) {
				reviews.add(new Review(review));
			}
			return ResponseEntity.ok(reviews);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createReview(@RequestBody ReviewDto reviewDto) throws Exception {
	    try {
			reviewService.createReview(reviewDto);
			return ResponseEntity.ok("Success Create!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateMember(@RequestBody ReviewDto reviewDto) throws Exception  {
		try {
			reviewService.updateReview(reviewDto);
			return ResponseEntity.ok("Success Update!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteMember(@RequestBody SearchRequest searchRequest) throws Exception {
		try {
			reviewService.deleteReview(searchRequest);
			return ResponseEntity.ok("Success Delete!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
}