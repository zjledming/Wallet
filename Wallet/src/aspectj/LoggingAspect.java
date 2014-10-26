package aspectj;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {
	private Log logger = LogFactory.getLog(getClass());

	private String getSTime() {
		String startTime = "";
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			startTime = sim.format(new Date());
		} catch (Exception e) {
			startTime = "0";
		}
		return startTime;
	}

	private String getETime() {
		String endTime = "";
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			endTime = sim.format(new Date());
		} catch (Exception e) {
			endTime = "0";
		}
		return endTime;
	}

	private long getSpentTime(String sT, String eT) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long spentSecs = 0L;
		try {
			spentSecs = (sim.parse(eT).getTime() - sim.parse(sT).getTime()) / 1000L;
		} catch (Exception e) {
			spentSecs = 0L;
		}
		return spentSecs;
	}

//	@Before("execution(* aspectj.service.*.*(..))")
//	public void doCheckBefore(JoinPoint joinPoint) {
//		System.out.println("Service Method "
//				+ joinPoint.getSignature().getName() + " Invocation!");
//	}

	@Around("execution(* aspectj.service.*.*(..))")
	public Object doCheckAround(ProceedingJoinPoint invocation)
			throws Throwable {
		String startTime = "";
		String endTime = "";
		startTime = getSTime();
		Object obj = invocation.proceed();
		endTime = getETime();

		System.out.println("total time spent>>>>>>>>>>>>"
				+ getSpentTime(startTime, endTime));
		return obj;
	}
}