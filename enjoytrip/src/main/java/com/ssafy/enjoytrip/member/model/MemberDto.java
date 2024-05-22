package com.ssafy.enjoytrip.member.model;

import lombok.Data;
@Data
public class MemberDto {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String emailId;
	private String emailDomain;
	private String memberBirth;
	private String refreshToken;
	private String imageName;
	private String imageUrl;
}