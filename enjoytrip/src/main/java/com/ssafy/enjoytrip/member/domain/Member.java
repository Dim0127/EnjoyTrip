package com.ssafy.enjoytrip.member.domain;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

import com.ssafy.enjoytrip.member.model.MemberDto;

import lombok.Data;

@Data
public class Member {
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String emailId;
	private String emailDomain;
	private String memberBirth;
	private URL imageUrl;
	
	public Member(MemberDto memberDto) throws Exception{
		this.memberId = memberDto.getMemberId();
		this.memberName = memberDto.getMemberName();
		this.memberPassword = memberDto.getMemberPassword();
		this.emailId = memberDto.getEmailId();
		this.emailDomain = memberDto.getEmailDomain();
		this.memberBirth = memberDto.getMemberBirth();

		try{
			this.imageUrl = new URL(memberDto.getImageUrl());			
		}catch(MalformedURLException e) {
			this.imageUrl = null;
		}
	}
}