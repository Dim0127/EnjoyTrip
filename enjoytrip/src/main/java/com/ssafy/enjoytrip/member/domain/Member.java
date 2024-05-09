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
	private Date joinDate;
	private Date memberBirth;
	private URL imageUrl;
	
	public Member(MemberDto memberDto) throws Exception{
		this.memberId = memberDto.getMemberId();
		this.memberName = memberDto.getMemberName();
		this.memberPassword = memberDto.getMemberPassword();
		this.emailId = memberDto.getEmailId();
		this.emailDomain = memberDto.getEmailDomain();
		this.joinDate = memberDto.getJoinDate();

		try{
			this.imageUrl = new URL(memberDto.getImageUrl());			
		}catch(MalformedURLException e) {
			this.imageUrl = new URL("https://www.google.com/search?q=imgae&oq=imgae&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIPCAEQABgKGIMBGLEDGIAEMg8IAhAAGAoYgwEYsQMYgAQyDAgDEAAYChixAxiABDIMCAQQABgKGLEDGIAEMg8IBRAAGAoYgwEYsQMYgAQyDAgGEAAYChixAxiABDIPCAcQABgKGIMBGLEDGIAEMg8ICBAAGAoYgwEYsQMYgAQyCQgJEAAYChiABNIBCDE0MjNqMGo3qAIIsAIB&sourceid=chrome&ie=UTF-8#vhid=m57Fdarda7KehM&vssid=l");	
		}
	}
}