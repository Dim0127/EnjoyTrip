package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;

public interface HotplaceService {
	List<HotplaceDto> getAllHotplace();
	HotplaceDto getHotplace(String hotplaceId);
	void createHotplace(HotplaceDto hotplaceDto);
	void deleteHotplace(String hotplaceId);
}
