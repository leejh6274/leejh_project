package com.ljh.exam.demo.utill;

public class Ut {

	public static boolean empty(Object obj) {
		//null 체크
		if (obj == null) {
			return true;
		}
		//string 체크
		if (obj instanceof String == false) {
			return true;
		}

		String str = (String) obj;

		return str.trim().length() == 0;
	}
	public static String f(String format, Object...args) {
		return String.format(format, args);
	}

}
