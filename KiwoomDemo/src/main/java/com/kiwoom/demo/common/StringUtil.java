package com.kiwoom.demo.common;

public class StringUtil {
	
	public static String nvl(Object obj) {
		if(obj == null) {
			return "";
		}else {
			return obj.toString();
		}
	}
}
