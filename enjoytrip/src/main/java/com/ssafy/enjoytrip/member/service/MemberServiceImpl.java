package com.ssafy.enjoytrip.member.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.member.mapper.MemberMapper;
import com.ssafy.enjoytrip.member.model.LoginRequest;
import com.ssafy.enjoytrip.member.model.MemberDto;
@Service
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return memberMapper.login(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String memberId) throws Exception {
		return memberMapper.userInfo(memberId);
	}

	@Override
	public void saveRefreshToken(String memberId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("저장합니다요옹");
		map.put("memberId", memberId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String memberId) throws Exception {
		return memberMapper.getRefreshToken(memberId);
	}

	@Override
	public void deleRefreshToken(String memberId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}
	
	@Override
	public Boolean isIdDuplicated(String memberId) throws SQLException {
		return memberMapper.isIdDuplicated(memberId)>0 ? true : false;
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(LoginRequest loginRequest) throws SQLException {
		return memberMapper.loginMember(loginRequest);
	}

	@Override
	public MemberDto getMember(String memberId) throws SQLException {
		return memberMapper.getMember(memberId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws SQLException {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(String memberId) throws SQLException {
		memberMapper.deleteMember(memberId);
	}

}
