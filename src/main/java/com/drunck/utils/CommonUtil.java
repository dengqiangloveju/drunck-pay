package com.drunck.utils;

public class CommonUtil {
	public static boolean isEmpty(Object content) {
		if(content==null || "".equals(content.toString().trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty(Object content) {
		if(isEmpty(content)) {
			return false;
		} else {
			return true;
		}
	}
}
