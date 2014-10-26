package aspectj;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import aspectj.service.*;
public class TestAspectj {

	public void aspectjShow(){
		MyService m=new MyService();
		m.theMethod();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Log4jEnvironment.configLog4j();
		TestAspectj t=new TestAspectj();
		t.aspectjShow();
	}

}
