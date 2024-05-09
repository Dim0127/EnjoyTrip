package com.ssafy.enjoytrip.member.model;

import java.sql.Date;
import lombok.Data;
@Data
public class MemberDto {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String emailId;
	private String emailDomain;
	private String memberBirth;
	private String imageUrl;
}