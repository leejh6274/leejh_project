package com.ljh.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ljh.exam.demo.repository.MemberRepository;
import com.ljh.exam.demo.vo.Member;

@Service
public class MemberService {

   private MemberRepository memberRepository;

   public MemberService(MemberRepository memberRepository) {
      this.memberRepository = memberRepository;

   }

//   public Member getMember(String loginId) {
//      return memberRepository.getMember(loginId);
//   }
//   
   public List<Member> getMembers() {
      return memberRepository.getMembers();
   }

   public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {

      // 로그인 아이디 중복체크
      Member oldMember = getMemberByLoginId(loginId);

      if (oldMember != null) {
         return -1;
      }
      oldMember = getMemberByNameAndEmail(name,email);
      // 이름 + 이메일 중복체크
      if (oldMember != null) {
         return -2;
      }

      memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);

      return memberRepository.getLastInsertId();
   }

   private Member getMemberByNameAndEmail(String name, String email) {
      return memberRepository.getMemberByNameAndEmail(name, email);
   }


   private Member getMemberByLoginId(String loginId) {
      return memberRepository.getMemberByLoginId(loginId);
   }

   public Member getMemberById(int id) {
      return memberRepository.getMemberById(id);
   }

}