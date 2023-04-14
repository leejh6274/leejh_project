package com.ljh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {


   @RequestMapping("/user/home/main")
   @ResponseBody
   public String showMain() {
      return "Main입니다.";
   }

}