package com.ssafy.enjoytrip.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.review.model.ReviewDto;
import com.ssafy.enjoytrip.review.model.SearchRequest;

@Mapper
public interface ReviewMapper {
	List<ReviewDto> getAllReview(String hotplaceId);
	List<ReviewDto> getMyReviewList(String memberId);
	ReviewDto getMyReviewForHotplace(SearchRequest searchRequest);
	void createReview(ReviewDto reviewDto);
	void updateReview(ReviewDto reviewDto);
	void deleteReview(SearchRequest searchRequest);
	String getFirstImage(String hotplaceId);
	void incrementHelpfulCount(SearchRequest searchRequest);
}