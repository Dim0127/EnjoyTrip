package com.ssafy.enjoytrip.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.ListParams;
import com.ssafy.enjoytrip.review.model.SearchRequest;
@Mapper
public interface HotplaceMapper {
	List<HotplaceDto> getAllHotplace(ListParams listParams);
	int getTotalHotplace(ListParams listParams);
	HotplaceDto getHotplace(String hotplaceId);
	void createHotplace(HotplaceDto hotplaceDto);
	void deleteHotplace(String hotplaceId);
	HotplaceDto selectHotplaceWithReview(SearchRequest searchRequest);
}