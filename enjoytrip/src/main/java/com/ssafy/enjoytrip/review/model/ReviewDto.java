package com.ssafy.enjoytrip.review.model;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class ReviewDto {
	private String hotplaceId;
	private String memberId;
	private int score;
	private String comment;
	private Timestamp createdAt;
	private int helpfulCount;
}