package com.ssafy.enjoytrip.hotplace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
@Mapper
public interface HotPlaceMapper {
	List<HotPlaceDto> getAllHotPlace();
	HotPlaceDto getHotPlace(String hotplaceId);
}