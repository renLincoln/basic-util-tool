package com.sinoiov.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author liweijie
 *
 * aes 加解密工具类
 *
 */
public class AESUtil {
	private final static String algorithm = "AES";

	private final static String rawKey ="CTFO_ZJHL_BSAPP_";

	private static AESUtil instance = new AESUtil();

	public static AESUtil getInstance() {
		return instance;
	}

	/**
	 * BASE64解密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private  byte[] decryptBASE64(String key) throws Exception {
		return Base64_URl.base64DecodeToArray(key);
	}

	/**
	 * BASE64加密
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private String encryptBASE64(byte[] key) throws Exception {
		return Base64_URl.base64EncodeFoArray(key);
	}

	/**
	 * 加密 String 明文输入 ,String 密文输出
	 */
	public String encryptStr(String data) {
		byte[] key = rawKey.getBytes();
		// Instantiate the cipher
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] encrypted = cipher.doFinal(data.getBytes());
			return encryptBASE64(encrypted);
		} catch (Exception e) {
			// App.log.info("AES", "获取加密串出错," + e.getMessage());
			return null;
		}

	}

	/**
	 * 解密 以 String 密文输入 ,String 明文输出
	 *
	 * @param strMi
	 * @return
	 */
	public String decryptStr(String encrypted) {
		try {
			byte[] tmp = decryptBASE64(encrypted);
			byte[] key = rawKey.getBytes();

			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] decrypted = cipher.doFinal(tmp);
			return new String(decrypted);
		} catch (Exception e) {
			// App.log.info("AES", "获取解密串出错," + e.getMessage());
			return null;
		}

	}
}
