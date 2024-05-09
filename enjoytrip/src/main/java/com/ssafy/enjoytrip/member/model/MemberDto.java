package com.ssafy.enjoytrip.member.model;

import java.sql.Date;
import lombok.Data;
@Data
public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String emailId;
	private String emailDomain;
	private Date joinDate;
	private Date memberBirth;
	private String imageUrl;
}