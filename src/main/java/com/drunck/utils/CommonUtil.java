package com.drunck.utils;

import java.util.UUID;

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
	
	public static String gitUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		for(int i=1; i<19; i++) {
			System.out.println(i+"--------->"+gitUUID());
		}
	}
}
