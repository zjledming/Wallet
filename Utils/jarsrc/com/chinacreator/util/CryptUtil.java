/*     */ package com.chinacreator.util;
/*     */ 
/*     */ import java.security.Key;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class CryptUtil
/*     */ {
/*     */   private static final String key = "creator";
/*     */ 
/*     */   private static Key initKeyForAES(String key)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  23 */     if ((key == null) || (key.length() == 0)) {
/*  24 */       throw new NullPointerException("key not is null");
/*     */     }
/*  26 */     SecretKeySpec key2 = null;
/*     */     try {
/*  28 */       KeyGenerator kgen = KeyGenerator.getInstance("AES");
/*  29 */       SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
/*  30 */       secureRandom.setSeed(key.getBytes());
/*     */ 
/*  32 */       kgen.init(128, secureRandom);
/*     */ 
/*  34 */       SecretKey secretKey = kgen.generateKey();
/*  35 */       byte[] enCodeFormat = secretKey.getEncoded();
/*     */ 
/*  37 */       key2 = new SecretKeySpec(enCodeFormat, "AES");
/*     */     } catch (NoSuchAlgorithmException ex) {
/*  39 */       throw new NoSuchAlgorithmException();
/*     */     }
/*  41 */     return key2;
/*     */   }
/*     */ 
/*     */   public static String encrypt(String source)
/*     */   {
/*     */     try
/*     */     {
/*  51 */       SecretKeySpec secretKey = (SecretKeySpec)initKeyForAES("creator");
/*  52 */       Cipher cipher = Cipher.getInstance("AES");
/*  53 */       byte[] byteContent = source.getBytes("utf-8");
/*  54 */       cipher.init(1, secretKey);
/*  55 */       byte[] result = cipher.doFinal(byteContent);
/*     */ 
/*  57 */       return asHex(result);
/*     */     }
/*     */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */   public static String decrypt(String source)
/*     */   {
/*     */     try
/*     */     {
/*  70 */       SecretKeySpec secretKey = (SecretKeySpec)initKeyForAES("creator");
/*  71 */       Cipher cipher = Cipher.getInstance("AES");
/*  72 */       cipher.init(2, secretKey);
/*  73 */       byte[] s = asBytes(source);
/*     */ 
/*  75 */       byte[] result = cipher.doFinal(s);
/*     */ 
/*  77 */       return new String(result);
/*     */     }
/*     */     catch (Exception e) {
/*  80 */       e.printStackTrace();
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   private static String asHex(byte[] buf)
/*     */   {
/*  87 */     StringBuffer strbuf = new StringBuffer(buf.length * 2);
/*     */ 
/*  89 */     for (int i = 0; i < buf.length; i++) {
/*  90 */       if ((buf[i] & 0xFF) < 16)
/*  91 */         strbuf.append("0");
/*  92 */       strbuf.append(Long.toString(buf[i] & 0xFF, 16));
/*     */     }
/*  94 */     return strbuf.toString();
/*     */   }
/*     */ 
/*     */   private static byte[] asBytes(String hexStr)
/*     */   {
/* 100 */     if (hexStr.length() < 1)
/* 101 */       return null;
/* 102 */     byte[] result = new byte[hexStr.length() / 2];
/* 103 */     for (int i = 0; i < hexStr.length() / 2; i++) {
/* 104 */       int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
/* 105 */       int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
/* 106 */       result[i] = (byte)(high * 16 + low);
/*     */     }
/* 108 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\android\Desktop\EncryptTools.jar
 * Qualified Name:     com.chinacreator.util.CryptUtil
 * JD-Core Version:    0.6.1
 */