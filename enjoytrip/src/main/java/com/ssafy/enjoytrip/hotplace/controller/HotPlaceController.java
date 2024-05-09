package com.ssafy.enjoytrip.hotplace.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.hotplace.model.HotPlaceDto;
import com.ssafy.enjoytrip.hotplace.service.HotPlaceService;
import com.ssafy.enjoytrip.member.domain.Member;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/hotplaces")
public class HotPlaceController {
	private final HotPlaceService hotPlaceService;
	
	public HotPlaceController(HotPlaceService hotPlaceService) {
		super();
		this.hotPlaceService = hotPlaceService;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllHotPlace() throws Exception {
	    try {
			List<HotPlaceDto> allHotPlace = hotPlaceService.getAllHotPlace();
			return ResponseEntity.ok(allHotPlace);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/{hotplaceId}")
	public ResponseEntity<?> getHotPlace(@PathVariable String hotplaceId) throws Exception {
	    try {
			HotPlaceDto hotPlace = hotPlaceService.getHotPlace(hotplaceId);
			return ResponseEntity.ok(hotPlace);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
}