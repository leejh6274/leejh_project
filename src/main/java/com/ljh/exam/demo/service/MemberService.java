package com.ljh.exam.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.ljh.exam.demo.repository.MemberRepository;
import com.ljh.exam.demo.vo.Member;

@Service
public class MemberService {

	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId); 
		
		if(oldMember != null) {
			return -1;
		}
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		return memberRepository.getLastInsertId();
	}
	
	private Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	
	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	
}
