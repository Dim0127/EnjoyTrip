package com.ssafy.enjoytrip.review.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

import com.ssafy.enjoytrip.member.model.MemberDto;

import lombok.Data;
@Data
public class ReviewDto {
	private String hotplaceId;
	private String memberId;
	private Integer score;
	private String comments;
	private String imageUrl;
	private Date createdAt;
}