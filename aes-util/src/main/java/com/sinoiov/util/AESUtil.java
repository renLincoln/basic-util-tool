package com.sinoiov.util;


import com.baomidou.mybatisplus.annotation.TableId;
import com.sinoiov.anno.AesAnnoField;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * @author liweijie
 *
 * aes 加解密工具类
 *
 */
public class AESUtil {
	private static Log log = LogFactory.getLog(AESUtil.class);
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
			log.error("获取加密串出错," + e.getMessage());
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
			log.error("获取解密串出错," + e.getMessage());
			// App.log.info("AES", "获取解密串出错," + e.getMessage());
			return null;
		}

	}


	/**
	 * @Author renhao
	 * @Description: 通过java反射、自定义注解实现需要部分字段的加密，并清除调用额外的不需要的参数
	 * @Date: 2021/5/18 15:10
	 * @Param: [targetObj(目标实体类), clearFlag(非注解字段清空标志), encodeRules(密钥), encodeFlag(加密解密标志 ， true为加密)]
	 * @Return: boolean
	 */
	public <T> boolean aesConvert(T targetObj, boolean clearFlag, String encodeRules, boolean encodeFlag) throws NullPointerException, IllegalAccessException {
		// 声明对象是否被加密标志
		Boolean haveAesFlag = false;
		// 参数校验
		if (ObjectUtils.isEmpty(targetObj)) {
			throw new NullPointerException();
		}
		// 获取对象的class类
		Class<?> targetClass = targetObj.getClass();
		// 获取此对象的各个属性
		Field[] fields = targetClass.getDeclaredFields();
		// 遍历属性，查询对象属性是否被自定义注解修饰
		for (Field field : fields) {
			// 字段被注解修饰
			field.setAccessible(true);
			if (field.isAnnotationPresent(AesAnnoField.class)) {
				// 获取当前实体类对应字段值
				Object fieldObj = field.get(targetObj);
				if (fieldObj == null || StringUtils.isEmpty(fieldObj.toString())) {
					// 若属性值为空，则直接跳过当前循环，执行一次属性遍历
					continue;
				}
				String fieldStr = fieldObj.toString();
				if (encodeFlag) {
					field.set(targetObj, AESUtil.getInstance().encryptStr(fieldStr));
				} else {
					field.set(targetObj, AESUtil.getInstance().decryptStr(fieldStr));
				}
				haveAesFlag = true;
			} else if (clearFlag && !field.isAnnotationPresent(TableId.class)) {
				// 清空对象当前字段值
				field.set(targetObj, null);
			}
		}
		return haveAesFlag;
	}

	public static void main(String[] str){

//		加密前：test
//		加密后：73C58BAFE578C59366D8C995CD0B9D6D
//		解密后：test
		System.out.println(AESUtil.getInstance().encryptStr("15286844683")); //5LPdAornzr0Ou/buv9s0Lg==
		System.out.println("rrra;"+AESUtil.getInstance().decryptStr("tPcNNHIBuKGZFE7qxQKkew=="));
	}
}
