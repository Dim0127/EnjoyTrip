package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.mapper.HotPlaceMapper;
import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
@Service
public class HotPlaceServiceImpl implements HotPlaceService {
	private HotPlaceMapper hotPlaceMapper;
	
	public HotPlaceServiceImpl(HotPlaceMapper hotPlaceMapper) {
		this.hotPlaceMapper = hotPlaceMapper;
	}
	@Override
	public List<HotPlaceDto> getAllHotPlace() {
		return hotPlaceMapper.getAllHotPlace();
	}

	@Override
	public HotPlaceDto getHotPlace(String hotplaceId) {
		return hotPlaceMapper.getHotPlace(hotplaceId);
	}
}