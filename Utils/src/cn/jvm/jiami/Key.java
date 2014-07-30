package cn.jvm.jiami;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 生成des算法中的key
 * 
 * @author ximing.fu
 * 
 */
public class Key {

	private String keyName;

	public Key() {

	}

	public Key(String keyName) {
		this.keyName = keyName;
	}

	public void createKey(String keyName) throws Exception {

		SecureRandom sr = new SecureRandom();
		/*
		 * 提供（对称）密钥生成器的功能。 
		 */
		//返回生成指定算法的秘密密钥的 KeyGenerator 对象。
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		//  初始化此密钥生成器
		kg.init(sr);
		// 生成一个密钥
		SecretKey key = kg.generateKey();
		System.out.println(key.toString());
		// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回 null。
		byte rawKeyData[] = key.getEncoded();
		// 将密钥保存到本地文件中
		FileOutputStream fo = new FileOutputStream(new File(keyName));
		fo.write(rawKeyData);
		fo.close();
	}

	public static void main(String args[]) {
		try {
			new Key("").createKey("d:/key.txt");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}