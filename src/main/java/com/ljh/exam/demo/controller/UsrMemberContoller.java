package com.ljh.exam.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljh.exam.demo.service.MemberService;
import com.ljh.exam.demo.utill.Ut;
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
		
		if ( id == -1 ) {
			return Ut.f("해당 로그인 아이디(%s)는 이미 사용중입니다.", loginId);
		}
		
		if ( id == -2 ) {
			return Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email);
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