package com.ssafy.enjoytrip.member.model;

import lombok.Data;

@Data
public class LoginRequest {
	private String memberId;
	private String memberPassword;
}