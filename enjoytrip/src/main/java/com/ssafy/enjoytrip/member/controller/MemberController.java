package com.ssafy.enjoytrip.member.controller;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
			MemberDto tmpMember = memberService.loginMember(loginRequest);
			if(tmpMember==null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 회원입니다. 아이디 또는 비밀번호를 확인해주세요.");	
			}
			Member member = new Member(tmpMember);
			return ResponseEntity.ok().body(member);
		}catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/isIdDuplicated/{memberId}")
	public ResponseEntity<?> isIdDuplicated(@PathVariable String memberId) throws Exception {
	    System.out.println("get");
		try {
			Boolean result = memberService.isIdDuplicated(memberId);
			if(result) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요.");
			}
			return ResponseEntity.ok(result);
		}catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> joinMember(@RequestBody MemberDto member) throws Exception {
	    try {
	    	//비밀번호 암호화
			memberService.joinMember(member);
			return ResponseEntity.ok("Success Join!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMember(@PathVariable String memberId) throws Exception {
	    System.out.println("get");
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