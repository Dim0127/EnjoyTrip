package com.ssafy.enjoytrip.review.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest {
	private String hotplaceId;
	private String memberId;
}