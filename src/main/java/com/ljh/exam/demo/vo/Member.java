package com.ljh.exam.demo.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private int id;
	private Date regDate;
	private Date updateDate;
	private String loginId;
	private String loginPw;
	private int authLevel;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;
	private boolean delStatus;
	private String delDate;
	
}
