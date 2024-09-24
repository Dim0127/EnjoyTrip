package com.ssafy.enjoytrip.helpful.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.helpful.mapper.HelpfulMapper;
import com.ssafy.enjoytrip.helpful.model.HelpfulDto;
@Service
public class HelpfulServiceImpl implements HelpfulService {
	private HelpfulMapper helpfulMapper;
	
	public HelpfulServiceImpl(HelpfulMapper helpfulMapper) {
		this.helpfulMapper = helpfulMapper;
	}
	
	@Override
	public
	void insertHelpful(String hotplaceId, String writerId, String memberId) {
		helpfulMapper.insertHelpful(hotplaceId, writerId, memberId);
	}

	@Override
	public
	void deleteHelpful(String hotplaceId, String writerId, String memberId) {
		helpfulMapper.deleteHelpful(hotplaceId, writerId, memberId);
	}

	@Override
	public List<HelpfulDto> countHelpful(String hotplaceId, String writerId) {
		return helpfulMapper.countHelpful(hotplaceId, writerId);
	}

	@Override
	public HelpfulDto isPushedHelpful(String hotplaceId, String writerId, String memberId) {
		return helpfulMapper.isPushedHelpful(hotplaceId, writerId, memberId);
	}

}
