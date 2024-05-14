package com.ssafy.enjoytrip.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
@Mapper
public interface HotplaceMapper {
	List<HotplaceDto> getAllHotplace();
	HotplaceDto getHotplace(String hotplaceId);
	void createHotplace(HotplaceDto hotplaceDto);
}