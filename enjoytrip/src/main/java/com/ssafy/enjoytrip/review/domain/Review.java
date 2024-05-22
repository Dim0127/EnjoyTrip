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
	private String comment;
	private Timestamp createdAt;
	private String imageName;
	private URL imageUrl;
	private Integer helpfulCount;
	
	public Review(ReviewDto reviewDto) throws Exception{
		this.hotplaceId = reviewDto.getHotplaceId();
		this.memberId = reviewDto.getMemberId();
		this.score = reviewDto.getScore();
		this.comment = reviewDto.getComment();
		this.imageName = reviewDto.getImageName();
		this.createdAt = reviewDto.getCreatedAt();try{
			this.imageUrl = new URL(reviewDto.getImageUrl());			
		}catch(MalformedURLException e) {
			this.imageUrl = null;
		}
		this.helpfulCount = reviewDto.getHelpfulCount();
	}
}
