package cn.jvm.jiami;

import java.io.File;  
import java.io.FileInputStream;  
import java.lang.reflect.Method;  
import java.security.SecureRandom;  
  
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;  
  
public class Decrypt {  
  
    public static void main(String[] args) throws Exception {  
  
        SecureRandom sr = new SecureRandom();  
        FileInputStream fi = new FileInputStream(new File("d:/key.txt"));  
        byte rawKeyData[] = new byte[fi.available()];  
        fi.read(rawKeyData);  
        fi.close();  
        DESKeySpec dks = new DESKeySpec(rawKeyData);  
        SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
        Cipher cipher = Cipher.getInstance("DES");  
        cipher.init(Cipher.DECRYPT_MODE, key, sr);  
        FileInputStream fi2 = new FileInputStream(new File(  
                "D:/HelloWorld.class"));  
        byte encryptedData[] = new byte[fi2.available()];  
        fi2.read(encryptedData);  
        fi2.close();  
        byte decryptedData[] = cipher.doFinal(encryptedData);  
        MyClassLoader mcl = new MyClassLoader("D:/");  
        Class cl = mcl.loadClass(decryptedData, "HelloWorld");  
        Method mainMethod = cl.getMethod("sayHello");  
        mainMethod.invoke(null, null);  
    }  
}  