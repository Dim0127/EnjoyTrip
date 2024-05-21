package com.ssafy.enjoytrip.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.review.mapper.ReviewMapper;
import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;

@Service
public class ReviewServiceImpl implements ReviewService{
	private final ReviewMapper reviewMapper;
	
	ReviewServiceImpl(ReviewMapper reviewMapper){
		this.reviewMapper = reviewMapper;
	}
	
	@Override
	public List<ReviewDto> getAllReview(String hotplaceId) {
		return reviewMapper.getAllReview(hotplaceId);
	}

	@Override
	public List<ReviewDto> getMyReviewList(String memberId) {
		return reviewMapper.getMyReviewList(memberId);
	}

	@Override
	public ReviewDto getMyReviewForHotplace(SearchRequest searchRequest) {
		return reviewMapper.getMyReviewForHotplace(searchRequest);
	}

	@Override
	public void createReview(ReviewDto reviewDto) {
		reviewMapper.createReview(reviewDto);
	}

	@Override
	public void updateReview(ReviewDto reviewDto) {
		reviewMapper.updateReview(reviewDto);
	}

	@Override
	public void deleteReview(SearchRequest searchRequest) {
		reviewMapper.deleteReview(searchRequest);
	}

	@Override
	public void incrementHelpfulCount(SearchRequest searchRequest) {
		reviewMapper.incrementHelpfulCount(searchRequest);
	}
}