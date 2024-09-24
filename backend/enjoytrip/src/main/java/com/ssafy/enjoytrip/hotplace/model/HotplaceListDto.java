package com.ssafy.enjoytrip.hotplace.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class HotplaceListDto {
	private List<HotplaceDto> hotplaceList;
	private int currentPage;
	private int totalPage;
}
