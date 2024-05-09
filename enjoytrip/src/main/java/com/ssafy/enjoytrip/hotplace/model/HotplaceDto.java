package com.ssafy.enjoytrip.hotplace.model;

import lombok.Data;

@Data
public class HotplaceDto {
	private String hotplaceId;
	private String hotplaceName;
	private Double hotplaceLag;
	private Double hotplaceLat;
	private String hotplaceAddress;
	private String hotplacePhone;
}