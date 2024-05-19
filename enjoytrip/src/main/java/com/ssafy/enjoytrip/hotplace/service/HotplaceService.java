package com.ssafy.enjoytrip.hotplace.service;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;
import com.ssafy.enjoytrip.hotplace.model.ListParams;

public interface HotplaceService {
	HotplaceListDto getAllHotplace(ListParams listParams);
	HotplaceDto getHotplace(String hotplaceId);
	void createHotplace(HotplaceDto hotplaceDto);
	void deleteHotplace(String hotplaceId);
}
