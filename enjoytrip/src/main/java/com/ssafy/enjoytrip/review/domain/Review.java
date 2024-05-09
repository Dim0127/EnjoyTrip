package com.ssafy.enjoytrip.review.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

import com.ssafy.enjoytrip.review.model.ReviewDto;

import lombok.Data;
@Data
public class Review {
	private String hotplaceId;
	private String memberId;
	private Integer score;
	private String comments;
	private Timestamp createdAt;
	
	public Review(ReviewDto reviewDto) throws Exception{
		this.hotplaceId = reviewDto.getHotplaceId();
		this.memberId = reviewDto.getMemberId();
		this.score = reviewDto.getScore();
		this.comments = reviewDto.getComments();
		this.createdAt = reviewDto.getCreatedAt();
	}
}
