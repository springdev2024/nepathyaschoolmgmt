package com.example.demo;

import java.security.SecureRandom;

public class UsefulMethods {
	
	static String COOKIE_NAME = "SECUREID";

	static String sampleSpace = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	static int SIZE = sampleSpace.length();
	
	public static String getRandomString(int len) {
		
		SecureRandom random = new SecureRandom();
		
		String ans = "";
		
		for(int i = 1; i <= len; i ++) {
			int index = random.nextInt(SIZE);
			ans = ans + sampleSpace.charAt(index);  // sampleSpace[index]
		}
		
		return ans;
	}
	
}
