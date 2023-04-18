package com.ljh.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ljh.exam.demo.repository.MemberRepository;
import com.ljh.exam.demo.utill.Ut;
import com.ljh.exam.demo.vo.Member;
import com.ljh.exam.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;

	}

//	public Member getMember(String loginId) {
//		return memberRepository.getMember(loginId);
//	}
//	
	public List<Member> getMembers() {
		return memberRepository.getMembers();
	}

	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		// 로그인 아이디 중복체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}
		oldMember = getMemberByNameAndEmail(name, email);
		// 이름 + 이메일 중복체크
		if (oldMember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일은(%s)는 이미 사용중입니다.", name, email));
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		int id = memberRepository.getLastInsertId();
		return ResultData.from("S-1", Ut.f("회원가입이 완료되었습니다.", id));

	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
