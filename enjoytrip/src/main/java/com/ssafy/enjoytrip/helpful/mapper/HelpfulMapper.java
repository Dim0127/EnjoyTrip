package com.ssafy.enjoytrip.helpful.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.helpful.model.HelpfulDto;

@Mapper
public interface HelpfulMapper {
	void insertHelpful(String hotplaceId, String writerId, String memberId);
	void deleteHelpful(String hotplaceId, String writerId, String memberId);
	List<HelpfulDto> countHelpful(String hotplaceId, String writerId);
}