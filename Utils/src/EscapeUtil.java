import org.junit.Test;


public class EscapeUtil {

	/**
	 * 1.�㡰.����java�е�ת���ַ��ǡ�u002E�����������е�ת�塰\\.��
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
	 * \\\\ת��֮��Ϊ\\
	 */
	@Test
	public void test(){
		System.out.println("ce\\.abc\\\\");
	}
	
	/**
	 * "ת��
	 */
	@Test
	public void test1(){
		System.out.println("\"");
	}
	
}
