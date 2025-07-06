package com.cart.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode("123456"); // 這邊可以換成你要加密的新密碼
		System.out.println("加密後的密碼是: " + encoded);
	}
}
