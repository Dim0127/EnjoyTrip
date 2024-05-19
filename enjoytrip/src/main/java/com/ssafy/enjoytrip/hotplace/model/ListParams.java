package com.ssafy.enjoytrip.hotplace.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ListParams {
	private Integer sizePerPage;
	private Integer currentPage;
	private Integer start;
	private String filter;
	private String keyword;
	private Boolean isDesc;
	
	public void calcStart() {
		start = currentPage * sizePerPage - sizePerPage;
	}
}
