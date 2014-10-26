package aspectj.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import aspectj.TestAspectj;

public class MyService {
	public void theMethod(){

		for(int i=0;i<10000;i++){
			int j=i*100/2;
		}
	}
}
