package cn.md5.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CryptUtil {
	private static final String key = "creator";

	private static Key initKeyForAES(String key)
			throws NoSuchAlgorithmException {
		if ((key == null) || (key.length() == 0)) {
			throw new NullPointerException("key not is null");
		}
		SecretKeySpec key2 = null;
		try {
			// AES:高级加密标准(AES--Advanced Encryption Standard)
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// SHA1PRNG:算法
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(key.getBytes());

			kgen.init(128, secureRandom);

			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();

			key2 = new SecretKeySpec(enCodeFormat, "AES");
		} catch (NoSuchAlgorithmException ex) {
			throw new NoSuchAlgorithmException();
		}
		return key2;
	}

	public static String encrypt(String source) {
		try {
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES("creator");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = source.getBytes("utf-8");
			cipher.init(1, secretKey);
			byte[] result = cipher.doFinal(byteContent);

			return asHex(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String source) {
		try {
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES("creator");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, secretKey);
			byte[] s = asBytes(source);

			byte[] result = cipher.doFinal(s);

			return new String(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String asHex(byte[] buf) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);

		for (int i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xFF) < 16)
				strbuf.append("0");
			strbuf.append(Long.toString(buf[i] & 0xFF, 16));
		}
		return strbuf.toString();
	}

	private static byte[] asBytes(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}