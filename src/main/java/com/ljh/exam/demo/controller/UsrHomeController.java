package com.ljh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {

   private int count;

   public UsrHomeController() {
      count = -1;
   }

   @RequestMapping("/user/home/main")
   @ResponseBody
   public String showMain() {
      return "안녕하세요";
   }

   @RequestMapping("/user/home/main2")
   @ResponseBody
   public String showMain2() {
      return "반갑습니다";
   }

   @RequestMapping("/user/home/main3")
   @ResponseBody
   public String showMain3() {
      return "또 만나요";
   }

   @RequestMapping("/user/home/getCount")
   @ResponseBody
   public int getCount() {

      return count;
   }

   @RequestMapping("/user/home/doSetCount")
   @ResponseBody
   public String doSetCount(int count) {
      this.count = count;

      return "count의 값이" + this.count + "으로 초기화";
   }

}