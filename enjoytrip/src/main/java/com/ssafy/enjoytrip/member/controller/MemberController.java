package com.ssafy.enjoytrip.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
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
import com.ssafy.enjoytrip.util.JWTUtil;



import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@CrossOrigin
@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {
	private final MemberService memberService;
	private final JWTUtil jwtUtil;
	
	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService =  memberService;
		this.jwtUtil = jwtUtil;
	}
	
	@Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getMemberId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getMemberId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token 을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getMemberId(), refreshToken);
				
//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
	@GetMapping("/mypage/{memberId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("memberId") @Parameter(description = "인증할 회원의 아이디.", required = true) String memberId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(memberId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
	@GetMapping("/logout/{memberId}")
	@Hidden
	public ResponseEntity<?> removeToken(@PathVariable ("memberId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(memberId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getMemberId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getMemberId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginMember(@RequestBody LoginRequest loginRequest) throws Exception {
//		try {
//			MemberDto tmpMember = memberService.loginMember(loginRequest);
//			if(tmpMember==null) {
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 회원입니다. 아이디 또는 비밀번호를 확인해주세요.");	
//			}
//			Member member = new Member(tmpMember);
//			return ResponseEntity.ok().body(member);
//		}catch(SQLException e) {
//			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
//		}
//	}
	
	@GetMapping("/isIdDuplicated/{memberId}")
	public ResponseEntity<?> isIdDuplicated(@PathVariable String memberId) throws Exception {
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
	
	@GetMapping("/isExistMember/{memberId}")
	public ResponseEntity<?> isExistMember(@PathVariable String memberId) throws Exception {
		try {
			Boolean result = memberService.isIdDuplicated(memberId);
			if(result) {
				return ResponseEntity.status(HttpStatus.OK).body("아이디가 확인되었습니다.");
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
		}catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	
	@PostMapping("/join")
	public ResponseEntity<?> joinMember(@RequestBody MemberDto member) throws Exception {
	    try {
	    	
			memberService.joinMember(member);
			return ResponseEntity.ok("Success Join!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
//	
//	@GetMapping("/{memberId}")
//	public ResponseEntity<?> getMember(@PathVariable String memberId) throws Exception {
//	    System.out.println("get");
//		try {
//			MemberDto tmpMember = memberService.getMember(memberId);
//			Member member = new Member(tmpMember);
//			return ResponseEntity.ok(member);
//		}catch(NoSuchElementException e) {
//			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
//		}
//	}
//	
	@PutMapping("/update/{memberId}")
	public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto) throws Exception  {
		try {
			memberService.updateMember(memberDto);
			return ResponseEntity.ok("Success Update!!!");
		}catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody MemberDto memberDto) throws Exception  {
		try {
			memberService.updatePassword(memberDto);
			return ResponseEntity.ok("Success PASSWORD Update!!!");
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