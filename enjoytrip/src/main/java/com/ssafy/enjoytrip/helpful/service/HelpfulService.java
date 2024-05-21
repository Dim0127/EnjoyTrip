package com.ssafy.enjoytrip.helpful.service;

import java.util.List;

import com.ssafy.enjoytrip.helpful.model.HelpfulDto;

public interface HelpfulService {
	void insertHelpful(String hotplaceId, String writerId, String memberId);
	void deleteHelpful(String hotplaceId, String writerId, String memberId);
	List<HelpfulDto> countHelpful(String hotplaceId, String writerId);
}
