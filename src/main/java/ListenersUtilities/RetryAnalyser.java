package ListenersUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	int max=5;
	int count=0;
	@Override
	public boolean retry(ITestResult arg0) {
		if(count<max)
		{
			count++;
			return true;
		}
		return false;
	}

}
