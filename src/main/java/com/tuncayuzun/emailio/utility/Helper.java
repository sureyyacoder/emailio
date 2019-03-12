package com.tuncayuzun.emailio.utility;

import java.util.Random;

public class Helper {

	public static boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public static String urlGenerator(String pathParam) {
		String path = "https://myemailio.com/reset?";

		return path + pathParam;
	}

}
