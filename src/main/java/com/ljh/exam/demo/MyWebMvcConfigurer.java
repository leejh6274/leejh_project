package com.ljh.exam.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ljh.exam.demo.interceptor.BeforeActionInterceptor;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	//BeforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	
	
	//이 아래 함수는 인터셉터를 적용하는 역할임
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
	}
	
	
}
