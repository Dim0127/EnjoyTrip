package com.ssafy.enjoytrip.review.service;

import java.util.List;

import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;

public interface ReviewService {
	List<ReviewDto> getAllReview(String hotplaceId);
	List<ReviewDto> getMyReviewList(String memberId);
	ReviewDto getMyReviewForHotplace(SearchRequest searchRequest);
	void createReview(ReviewDto reviewDto);
	void updateReview(ReviewDto reviewDto);
	void deleteReview(SearchRequest searchRequest);
	String getFirstImage(String hotplaceId);
	void incrementHelpfulCount(SearchRequest searchRequest);
}
