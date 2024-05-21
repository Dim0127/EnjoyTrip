package com.ssafy.enjoytrip.review.domain;

import java.sql.Timestamp;

import com.ssafy.enjoytrip.review.model.ReviewDto;

import lombok.Data;
@Data
public class Review {
	private String hotplaceId;
	private String memberId;
	private Integer score;
	private String comment;
	private Timestamp createdAt;
	private Integer helpfulCount;
	
	public Review(ReviewDto reviewDto) throws Exception{
		this.hotplaceId = reviewDto.getHotplaceId();
		this.memberId = reviewDto.getMemberId();
		this.score = reviewDto.getScore();
		this.comment = reviewDto.getComment();
		this.createdAt = reviewDto.getCreatedAt();
		this.helpfulCount = reviewDto.getHelpfulCount();
	}
}
