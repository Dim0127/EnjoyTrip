package com.ssafy.enjoytrip.hotplace.service;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;
import com.ssafy.enjoytrip.hotplace.model.ListParams;
import com.ssafy.enjoytrip.review.model.SearchRequest;

public interface HotplaceService {
	HotplaceListDto getAllHotplace(ListParams listParams);
	HotplaceDto getHotplace(String hotplaceId);
	void createHotplace(HotplaceDto hotplaceDto);
	void deleteHotplace(String hotplaceId);
	HotplaceDto selectHotplaceWithReview(SearchRequest searchRequest);
}
