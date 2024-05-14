package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.mapper.HotplaceMapper;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
@Service
public class HotplaceServiceImpl implements HotplaceService {
	private HotplaceMapper hotplaceMapper;
	
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		this.hotplaceMapper = hotplaceMapper;
	}
	
	@Override
	public List<HotplaceDto> getAllHotplace() {
		return hotplaceMapper.getAllHotplace();
	}

	@Override
	public HotplaceDto getHotplace(String hotplaceId) {
		return hotplaceMapper.getHotplace(hotplaceId);
	}
}