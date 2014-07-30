package cn.jvm.jiami;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 对class进行加密
 * @author ximing.fu
 *
 */
public class Crypt {

	public static void main(String[] args) throws Exception {

		/*
		 * 提供强加密随机数生成器 (RNG)
		 */
		SecureRandom sr = new SecureRandom();
		// 读取密要
		FileInputStream fi = new FileInputStream(new File("d:/key.txt"));
		byte rawKeyData[] = new byte[fi.available()];
		fi.read(rawKeyData);
		fi.close();
		// 指定一个 DES 密钥：创建一个 DESKeySpec 对象，使用 key 中的前 8 个字节作为 DES 密钥的密钥内容
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		// 秘密密钥的工厂：返回转换指定算法的秘密密钥的 SecretKeyFactory 对象，根据提供的密钥规范（密钥材料）生成 SecretKey 对象
		SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);
		/*
		 * 为加密和解密提供密码功能
		 */
		// 返回实现指定转换的 Cipher 对象
		Cipher cipher = Cipher.getInstance("DES");
		// 用取自给定证书的公钥和随机源初始化此 Cipher。
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		// 源代码
		FileInputStream fi2 = new FileInputStream(new File(
				"d:/HelloWorld.class"));
		byte data[] = new byte[fi2.available()];
		fi2.read(data);
		fi2.close();
		// 加密源代码：按单部分操作加密或解密数据，或者结束一个多部分操作。
		byte encryptedData[] = cipher.doFinal(data);
		FileOutputStream fo = new FileOutputStream(new File(
				"d:/HelloWorld.class"));
		fo.write(encryptedData);
		fo.close();
	}
}