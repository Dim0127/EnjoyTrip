package com.ssafy.enjoytrip.review.controller;

import java.net.URL;
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

import com.ssafy.enjoytrip.helpful.model.HelpfulDto;
import com.ssafy.enjoytrip.helpful.service.HelpfulService;
import com.ssafy.enjoytrip.review.domain.Review;
import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;
import com.ssafy.enjoytrip.review.service.ReviewService;

@CrossOrigin
@RestController
@RequestMapping("/reviews")
public class ReviewController {
private final ReviewService reviewService;
private final HelpfulService helpfulService;
	
	public ReviewController(ReviewService reviewService, HelpfulService helpfulService) {
		super();
		this.reviewService =  reviewService;
		this.helpfulService = helpfulService;
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
	public ResponseEntity<?> getMyReviewList(@PathVariable String memberId) throws Exception {
		try {
			List<ReviewDto> tmpReviews = reviewService.getMyReviewList(memberId);
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
	public ResponseEntity<?> getMyReviewForHotplace(@RequestBody SearchRequest searchRequest) throws Exception {
		try {
			ReviewDto tmpReview = reviewService.getMyReviewForHotplace(searchRequest);
			if(tmpReview == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 사용자는 해당 핫플레이스에 리뷰를 달지 않음");
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
	
	@PutMapping("/incrementHelpfulCount")
	public ResponseEntity<?> incrementHelpfulCount(@RequestBody SearchRequest searchRequest) {
		try {
			reviewService.incrementHelpfulCount(searchRequest);
			return ResponseEntity.status(HttpStatus.OK).body("SUCCESS INCREMENT");
		}catch(NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
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
	
	@GetMapping("/image/{hotplaceId}")
	public ResponseEntity<?> getFirstImage(@PathVariable String hotplaceId) throws Exception{
		try {
			System.out.println("이미지 가져오기 호출");
	        String imageUrl = reviewService.getFirstImage(hotplaceId);
	        if(imageUrl == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        return ResponseEntity.ok(new URL(imageUrl));
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/helpful/{hotplaceId}/{writerId}/{memberId}")
	public ResponseEntity<?> insertHelpful(@PathVariable String hotplaceId, @PathVariable String writerId, @PathVariable String memberId) throws Exception{
		try {
	        helpfulService.insertHelpful(hotplaceId, writerId, memberId);
	        return ResponseEntity.ok("Success INSERT HEALPFUL!!!");
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/helpful/{hotplaceId}/{writerId}/{memberId}")
	public ResponseEntity<?> deleteHelpful(@PathVariable String hotplaceId, @PathVariable String writerId, @PathVariable String memberId) throws Exception{
		try {
	        helpfulService.deleteHelpful(hotplaceId, writerId, memberId);
	        return ResponseEntity.ok("Success DELETE HEALPFUL!!!");
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/helpful/{hotplaceId}/{writerId}")
	public ResponseEntity<?> countHelpful(@PathVariable String hotplaceId, @PathVariable String writerId) throws Exception{
		try {
	        List<HelpfulDto> helpfulDtoList = helpfulService.countHelpful(hotplaceId, writerId);
	        int len = helpfulDtoList.size();
	        return ResponseEntity.ok(len);
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/helpful/isPushedHelpful/{hotplaceId}/{writerId}/{memberId}")
	public ResponseEntity<?> isPushedHelpful(@PathVariable String hotplaceId, @PathVariable String writerId, @PathVariable String memberId) throws Exception{
		try {
	        HelpfulDto HelpfulDto = helpfulService.isPushedHelpful(hotplaceId, writerId, memberId);
	        if(HelpfulDto!=null) {
	        	return ResponseEntity.ok(true);
	        }else {
	        	return ResponseEntity.ok(false);
	        }
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
}