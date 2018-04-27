package com.gl365.app.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于 {@link SecureRandom} 的随机数辅助类<br>
 * 
 * 由于基于 {@link SecureRandom}
 * ，在生成随机内容时速度相对会比较慢，甚至可能会产生阻塞，对于不要求高密码强度的随机内容，建议采用其他基于 {@link Random}
 * 的辅助方法（例如commons-lang 的 RandomStringUtils）。
 */
public class SecureRandomUtils {

	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * 生成指定位长度的随机数字符串
	 * 
	 * @param bitLength
	 *            位长度，位长度必须为8的倍数
	 */
	public static String randomString(int bitLength) {
		if ((bitLength & 7) != 0) {
			throw new IllegalArgumentException(
					"bitLength must be a multiplier of 8.");
		}
		byte[] r = randomBytes(bitLength >> 3);
		return Base64.encodeBase64URLSafeString(r);
	}

	public static byte[] randomBytes(int bytes) {
		byte[] r = new byte[bytes];
		RANDOM.nextBytes(r);
		return r;
	}

	public static long randomLong() {
		return RANDOM.nextLong();
	}

	public static int randomInt(int n) {
		return RANDOM.nextInt(n);
	}
}
