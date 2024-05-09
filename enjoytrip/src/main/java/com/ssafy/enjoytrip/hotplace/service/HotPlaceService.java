package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;

public interface HotPlaceService {
	List<HotPlaceDto> getAllHotPlace();
	HotPlaceDto getHotPlace(String hotplaceId);
}
