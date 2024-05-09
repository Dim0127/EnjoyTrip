package com.ssafy.enjoytrip.review.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

import com.ssafy.enjoytrip.review.model.ReviewDto;

import lombok.Data;
@Data
public class Review {
	private String hotplaceId;
	private String memberId;
	private Integer score;
	private String comments;
	private URL imageUrl;
	private Date createdAt;
	
	public Review(ReviewDto reviewDto) throws Exception{
		this.hotplaceId = reviewDto.getHotplaceId();
		this.memberId = reviewDto.getMemberId();
		this.score = reviewDto.getScore();
		this.comments = reviewDto.getComments();
		try{
			this.imageUrl = new URL(reviewDto.getImageUrl());			
		}catch(MalformedURLException e) {
			this.imageUrl = new URL("https://www.google.com/search?q=imgae&oq=imgae&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIPCAEQABgKGIMBGLEDGIAEMg8IAhAAGAoYgwEYsQMYgAQyDAgDEAAYChixAxiABDIMCAQQABgKGLEDGIAEMg8IBRAAGAoYgwEYsQMYgAQyDAgGEAAYChixAxiABDIPCAcQABgKGIMBGLEDGIAEMg8ICBAAGAoYgwEYsQMYgAQyCQgJEAAYChiABNIBCDE0MjNqMGo3qAIIsAIB&sourceid=chrome&ie=UTF-8#vhid=m57Fdarda7KehM&vssid=l");	
		}
		this.createdAt = reviewDto.getCreatedAt();
	}
}
