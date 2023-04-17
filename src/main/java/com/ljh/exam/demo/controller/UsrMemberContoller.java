package com.ljh.exam.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljh.exam.demo.service.MemberService;
import com.ljh.exam.demo.vo.Member;

@Controller
public class UsrMemberContoller {
	
	private MemberService memberService;
	
	public UsrMemberContoller(MemberService memberService) {
		this.memberService = memberService;
	}

	
   @RequestMapping("/usr/member/doJoin")
   @ResponseBody
   public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
	   
	   int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
	   
	   if(loginPw == null || loginPw.trim().length()==0) {
		   return "loginId(을)를 입력해주세요.";
	   }
	   if(loginId == null || loginId.trim().length()==0) {
		   return "loginPw(을)를 입력해주세요.";
	   }
	   if(name == null || name.trim().length()==0) {
		   return "name(을)를 입력해주세요.";
	   }
	   if(nickname == null || nickname.trim().length()==0) {
		   return "nickname(을)를 입력해주세요.";
	   }
	   if(cellphoneNo == null || cellphoneNo.trim().length()==0) {
		   return "cellphoneNo(을)를 입력해주세요.";
	   }
	   if(email == null || email.trim().length()==0) {
		   return "email(을)를 입력해주세요.";
	   }
	   
	   
	   
	   if(id == -1) {
		   return "해당 로그인 아이디는 이미 사용중입니다.";
	   }
	   
	   Member member = memberService.getMemberById(id);
	   
      return member;
   }
   
   @RequestMapping("/usr/member/getMembers")
   @ResponseBody
   public List<Member> getMembers() {
      return memberService.getMembers();
   }
   
   

}