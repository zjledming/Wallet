import org.junit.Test;

import com.sun.mail.handlers.text_html;


public class EscapeUtil {

	/**
	 * 1.点“.”在java中的转义字符是“u002E”，在正则中的转义“\\.”
	 * @param args
	 */
	public static void main(String[] args) {
		String reString = "ce.abc";
		String[] arr = reString.split("\\.");
		System.out.println(arr[1]);
		reString = "---ddd----ce.abc-------ccc----";
		String re = reString.replaceAll("ce\\.abc", "abc");
		System.out.println(re);
	}
	
	/**
	 * \\\\转移之后为\\
	 */
	@Test
	public void test(){
		System.out.println("ce\\.abc\\\\");
	}
	
	/**
	 * "转义
	 */
	@Test
	public void test1(){
		System.out.println("\"");
	}
	
}
