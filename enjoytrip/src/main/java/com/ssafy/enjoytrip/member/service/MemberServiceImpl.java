package com.ssafy.enjoytrip.member.service;

import java.sql.SQLException;

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
	public Boolean isIDAvailable(String memberId) throws SQLException {
		return memberMapper.isIDAvailable(memberId);
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
