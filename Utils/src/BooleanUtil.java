
public class BooleanUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean flag= "".equals("1") ;
		System.out.println(flag);
		 flag= "".equals("1") || "1".equals("1");
		System.out.println(flag);
		flag= "".equals("1") && "1".equals("1");
		System.out.println(flag);

	}

}
