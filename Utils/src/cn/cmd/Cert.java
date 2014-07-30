package cn.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * java调用cmd命令，如何模拟输入Y键
 * 
 * @author ximing.fu
 * 
 */
public class Cert {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exec("keytool -import -alias publiccert -file D:\\fea5c7ae-79f0-3c29-ab44-a042187ede42\\cert.cer -keystore D:\\publicCerts.store -storepass  publicstore123");
	}

	public static String exec(String cmd) {
		StringBuffer sb1;
		StringBuffer sb2;
		sb1 = new StringBuffer();
		sb2 = new StringBuffer();
		String temp1 = "";
		String temp2 = "";
		Runtime rt = Runtime.getRuntime();
		try {
			System.out.println(cmd);
			Process p = rt.exec(cmd);
			BufferedReader bufferedReader1 = new BufferedReader(
					new InputStreamReader(p.getInputStream(), "GBK"));
			BufferedReader bufferedReader2 = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			while ((temp1 = bufferedReader1.readLine()) != null) {
				sb1.append(temp1);
				System.out.println(temp1);
			}
			while ((temp2 = bufferedReader2.readLine()) != null) {
				sb2.append(temp2);
				System.out.println(temp2);
			}
			p.waitFor();
			return sb1.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb1.toString();
	}
}




