package com.ssafy.enjoytrip.review.model;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class ReviewDto {
	private String hotplaceId;
	private String memberId;
	private Integer score;
	private String comment;
	private String imageUrl;
	private Timestamp createdAt;
}