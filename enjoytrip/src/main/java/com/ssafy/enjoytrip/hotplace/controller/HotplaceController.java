package com.ssafy.enjoytrip.hotplace.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceListDto;
import com.ssafy.enjoytrip.hotplace.model.ListParams;
import com.ssafy.enjoytrip.hotplace.service.HotplaceService;
import com.ssafy.enjoytrip.review.model.SearchRequest;


@CrossOrigin
@RestController
@RequestMapping("/hotplaces")
public class HotplaceController {
	private final HotplaceService hotplaceService;
	
	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}
	
	@PostMapping("/get")
	public ResponseEntity<?> getAllHotplace(@RequestBody ListParams listParams) throws Exception {
	    try {
	    	HotplaceListDto allHotplace = hotplaceService.getAllHotplace(listParams);
			return ResponseEntity.ok(allHotplace);
		}catch(NoSuchElementException e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@GetMapping("/get/{hotplaceId}")
	public ResponseEntity<?> getHotplace(@PathVariable String hotplaceId) throws Exception {
	    try {
			HotplaceDto hotplace = hotplaceService.getHotplace(hotplaceId);
			return ResponseEntity.ok(hotplace);
		}catch(NoSuchElementException e) {
			return ResponseEntity.ok(false);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createHotplace(@RequestBody HotplaceDto hotplaceDto) throws Exception {
		try{
			hotplaceService.createHotplace(hotplaceDto);
			return ResponseEntity.ok("Success Create!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	// 관리자 권한
	@GetMapping("/delete/{hotplaceId}")
	public ResponseEntity<?> deleteHotplace(@PathVariable String hotplaceId) throws Exception {
	    try {
			hotplaceService.deleteHotplace(hotplaceId);
			return ResponseEntity.ok("Success Delete!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PostMapping("/selectHotplaceWithReview")
	public ResponseEntity<?> selectHotplaceWithReview(@RequestBody SearchRequest searchRequest) {
		try {
			HotplaceDto hotplaceDto = hotplaceService.selectHotplaceWithReview(searchRequest);
			return ResponseEntity.ok(hotplaceDto);
		}catch(NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());

		}
	}
	
	
}