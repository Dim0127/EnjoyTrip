package com.ssafy.enjoytrip.review.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.review.domain.Review;
import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;
import com.ssafy.enjoytrip.review.service.ReviewService;
@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {
private final ReviewService reviewService;
	
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService =  reviewService;
	}
	
	@GetMapping("/hotplace/{hotplaceId}")
	public ResponseEntity<?> getAllReview(@PathVariable String hotplaceId) throws Exception {
		try {
			List<ReviewDto> tmpReviews = reviewService.getAllReview(hotplaceId);
			List<Review> reviews = new ArrayList<Review>();
			for(ReviewDto review : tmpReviews) {
				reviews.add(new Review(review));
			}
			return ResponseEntity.ok(reviews);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/member/{memberId}")
	public ResponseEntity<?> getMemberReview(@PathVariable String memberId) throws Exception {
		try {
			List<ReviewDto> tmpReviews = reviewService.getMemberReview(memberId);
			List<Review> reviews = new ArrayList<Review>();
			for(ReviewDto review : tmpReviews) {
				reviews.add(new Review(review));
			}
			return ResponseEntity.ok(reviews);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> getReview(@RequestBody SearchRequest searchRequest) throws Exception {
		try {
			ReviewDto tmpReview = reviewService.getReview(searchRequest);
			if(tmpReview == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("해당 사용자는 해당 핫플레이스에 리뷰를 달지 않음");
			Review review = new Review(tmpReview);
			return ResponseEntity.ok(review);
		}catch(SQLException e) {
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
	public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) throws Exception  {
		try {
			reviewService.updateReview(reviewDto);
			return ResponseEntity.ok("Success Update!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{hotplaceId}/{memberId}")
	public ResponseEntity<?> deleteReview(@PathVariable String hotplaceId, @PathVariable String memberId) throws Exception {
	    try {
	    	SearchRequest searchRequest = new SearchRequest(hotplaceId, memberId);
	        reviewService.deleteReview(searchRequest);
	        return ResponseEntity.ok("Success Delete!!!");
	    } catch (NoSuchElementException e) {
	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
	    }
	}
}