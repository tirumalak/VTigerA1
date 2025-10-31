package ListenersUtilities;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.BaseClass;

public class Listeners implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public ExtentSparkReporter spark;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report configuration", true);
		String time = new Date().toString().replace(":", "_").replace(" ", "_");
		 spark=new ExtentSparkReporter("./AdvancedReportsVTiger/Report"+time+".html");
		 
		//report configuration
		spark.config().setDocumentTitle("CRM VTiger Report");
		spark.config().setResourceCDN("Contact_Org Reports");
		spark.config().setTheme(Theme.DARK);
		
		//Adding environment details
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS_VERSION", "windows-11");
		report.setSystemInfo("Browser-VERSION", "Chrome-141");
		
	}
	

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report backup", true);
		UtilityObject.getTest().log(Status.INFO, ""+"Report Backup");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution  started", true);
		test=report.createTest(testname);
		UtilityObject.setTest(test);
		UtilityObject.getTest().log(Status.INFO,""+testname+"Test  execution stated");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution  success", true);
		UtilityObject.getTest().log(Status.INFO, ""+testname+"Test  Execution Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution  failed", true);
		UtilityObject.getTest().log(Status.INFO, ""+testname+"Test execution Failed");
		
		String time = new Date().toString().replace(":", "_").replace(" ", "_");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		UtilityObject.getTest().addScreenCaptureFromBase64String(src+""+testname+time+"");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "Test Execution  skipped", true);
		UtilityObject.getTest().log(Status.INFO, ""+testname+"Test  execution Skipped");
	
	}

}
