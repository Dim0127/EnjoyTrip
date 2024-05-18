package com.ssafy.enjoytrip.member.service;

import java.sql.SQLException;

import com.ssafy.enjoytrip.member.model.LoginRequest;
import com.ssafy.enjoytrip.member.model.MemberDto;

public interface MemberService {
	MemberDto login(MemberDto memberDto) throws Exception;
	MemberDto userInfo(String memberId) throws Exception;
	void saveRefreshToken(String memberId, String refreshToken) throws Exception;
	Object getRefreshToken(String memberId) throws Exception;
	void deleRefreshToken(String memberId) throws Exception;
	
	//아이디 중복 체크
	Boolean isIdDuplicated(String memberId) throws SQLException;
	//회원가입
	void joinMember(MemberDto memberDto) throws SQLException;
	//로그인
	MemberDto loginMember(LoginRequest loginRequest) throws SQLException;
	//admin 권한으로 원하는 회원 정보 가져오기
	MemberDto getMember(String memberId) throws SQLException;
	//회원 정보 수정
	void updateMember(MemberDto memberDto) throws SQLException;
	//회원 정보 삭제
	void deleteMember(String memberId) throws SQLException;
}