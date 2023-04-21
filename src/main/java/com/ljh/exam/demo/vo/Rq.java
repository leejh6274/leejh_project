package com.ljh.exam.demo.vo;

import java.io.IOException;

import com.ljh.exam.demo.service.MemberService;
import com.ljh.exam.demo.utill.Ut;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Rq{
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	
	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession();
		
		boolean isLogined = false;
	    int loginedMemberId = 0;
	    
	    if (session.getAttribute("loginedMemberId") != null) {
	        isLogined = true;
	        loginedMemberId = (int) session.getAttribute("loginedMemberId");
	        loginedMember = memberService.getMemberById(loginedMemberId);
	    }
	    
	    this.isLogined = isLogined;
	    this.loginedMemberId = loginedMemberId;
	    this.loginedMember = loginedMember;
    }

	public void printHistroyBackjs(String msg) {/*
		resp.setContentType("text/html; charset=UTF-8");             => js.jsp안에 자바스크립트로 등록해서 주석친 애들 필요 없어짐.
		
		print("<script>");
		
		if(!Ut.empty(msg)) {
			println("alert('" + msg + "');");
		}
		print("history.back();");
		
		print("</script>");*/
		resp.setContentType("text/html; charset=UTF-8");
		
		print(Ut.jsHistoryBack(msg));
	}
	
	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void println(String str) {
		print(str + "\n");
		
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	
	public String historyBackJsOnview(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "common/js";
	}
	
	
	
}
