package com.ljh.exam.demo.vo;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.ljh.exam.demo.service.MemberService;
import com.ljh.exam.demo.utill.Ut;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

/*rq가 자동으로 의존성주입 된다 = component
객체가 하나만 존재하는게 아니라 각자의 요청에 따라 다 있어야 한다 = scope*/
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
	    
	    this.req.setAttribute("rq", this);
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

	public String jsHistoryBack(String msg) {
		return Ut.jsHistoryBack(msg);
	}
	
	public String jsReplace(String msg, String uri) {
		return Ut.jsReplace(msg, uri);
	}

	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString(); 
		
		if(queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}
		return currentUri;
	}
	
	public String getEncodedCurrentUri() {
		return Ut.getUriEncoded(getCurrentUri());
	}
	
	//이 메서드는 Rq객체가 자연스럽게 생성되도록 유도하는 역할을 한다.
	//지우면 절대 안되고,
	//편의를 위해 BefroeActionInterceptor에서 꼭 호출해줘야 한다.
	public void intiOnBeforeActionInterceptor() {
		
	}
	
	
	
}
