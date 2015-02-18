package com.softserve.edu.service;

import java.util.Random;

public interface RandomPass {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final int LEN = 6;
	static Random rnd = new Random();

	default String randomString() {
		StringBuilder sb = new StringBuilder(LEN);
		for (int i = 0; i < LEN; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
