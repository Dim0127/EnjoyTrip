package com.ssafy.enjoytrip.member.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.member.model.LoginRequest;
import com.ssafy.enjoytrip.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	MemberDto login(MemberDto memberDto) throws SQLException;
	MemberDto userInfo(String memberId) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String memberId) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	//아이디 중복 체크
	Integer isIdDuplicated(String memberId);
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