package com.ssafy.enjoytrip.member.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.domain.Member;
import com.ssafy.enjoytrip.member.model.LoginRequest;
import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		super();
		this.memberService =  memberService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(@RequestBody LoginRequest loginRequest) throws Exception {
	    try {
	    	//암호화된 비밀번호와 같은지 확인
			MemberDto tmpMember = memberService.loginMember(loginRequest);
			Member member = new Member(tmpMember);
			return ResponseEntity.ok(member);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> joinMember(@RequestBody MemberDto member) throws Exception {
	    try {
	    	//아이디 중복 체크
	    	//비밀번호 암호화 필요
			memberService.joinMember(member);
			return ResponseEntity.ok("Success Join!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMember(@PathVariable String memberId) throws Exception {
		try {
			MemberDto tmpMember = memberService.getMember(memberId);
			Member member = new Member(tmpMember);
			return ResponseEntity.ok(member);
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PutMapping("/update/{memberId}")
	public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto) throws Exception  {
		try {
			memberService.updateMember(memberDto);
			return ResponseEntity.ok("Success Update!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{memberId}")
	public ResponseEntity<?> deleteMember(@PathVariable String memberId) throws Exception {
		try {
			memberService.deleteMember(memberId);
			return ResponseEntity.ok("Success Delete!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
}