package com.ssafy.enjoytrip.hotplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.mapper.HotplaceMapper;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;
import com.ssafy.enjoytrip.hotplace.model.ListParams;
import com.ssafy.enjoytrip.review.model.SearchRequest;
@Service
public class HotplaceServiceImpl implements HotplaceService {
	private HotplaceMapper hotplaceMapper;
	
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		this.hotplaceMapper = hotplaceMapper;
	}
	
	@Override
	public HotplaceListDto getAllHotplace(ListParams listParams) {
		if(listParams.getSizePerPage() == null) listParams.setSizePerPage(15);
		if(listParams.getCurrentPage() == null) listParams.setCurrentPage(1);
		if(listParams.getIsDesc() == null) listParams.setIsDesc(false);
		listParams.calcStart();
		
		List<HotplaceDto> hotplaceList = hotplaceMapper.getAllHotplace(listParams);
		int totalHotplace = hotplaceMapper.getTotalHotplace(listParams);
		int totalPage = (totalHotplace - 1) / listParams.getSizePerPage() + 1;

		HotplaceListDto hotplacListDto = new HotplaceListDto(hotplaceList, listParams.getCurrentPage(), totalPage);
		return hotplacListDto;
	}

	@Override
	public HotplaceDto getHotplace(String hotplaceId) {
		return hotplaceMapper.getHotplace(hotplaceId);
	}
	
	@Override
	public void createHotplace(HotplaceDto hotplaceDto) {
		hotplaceMapper.createHotplace(hotplaceDto);
	}

	@Override
	public void deleteHotplace(String hotplaceId) {
		hotplaceMapper.deleteHotplace(hotplaceId);
	}

	@Override
	public HotplaceDto selectHotplaceWithReview(SearchRequest searchRequest) {
		return hotplaceMapper.selectHotplaceWithReview(searchRequest);
	}

	
}