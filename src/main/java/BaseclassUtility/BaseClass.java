package BaseclassUtility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import GenericUtilities.DatabaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import ListenersUtilities.UtilityObject;
import VTigerPomPages.HomePagePomPage;
import VTigerPomPages.LoginPomPage;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver;
	public PropertyFileUtility p = new PropertyFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public DatabaseUtility dbutil = new DatabaseUtility();

	@BeforeSuite
	public void ConnectToDB() throws SQLException {
		Reporter.log("Connected to Database",true);
		//UtilityObject.getTest().log(Status.INFO,"Connected to Database");
		dbutil.connectToDB();
	}

	@BeforeTest
	public void conParallelExe() {
		Reporter.log("Configuration of parallel exe",true);
	//	UtilityObject.getTest().log(Status.INFO,"Configuration of parallel exe");
	}

	 @Parameters("browser")
	@BeforeClass
	public void LaunchTheBrowser() throws Exception {
		Reporter.log("Launching the browser",true);
		//UtilityObject.getTest().log(Status.INFO,"Launching the browser");
		String browser = System.getProperty("browser",p.fetchDataFromPropertyFile("browser"));

		// Launch the browser
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		sdriver = driver;
		UtilityObject.setDriver(driver);
	}

	@BeforeMethod
	public void login() throws Exception {
		Reporter.log("Login to the application",true);
		//UtilityObject.getTest().log(Status.INFO,"Login to the application");
		String username =System.getProperty("username",p.fetchDataFromPropertyFile("username"));
		String password =System.getProperty("password",p.fetchDataFromPropertyFile("password"));
		String url = System.getProperty("url",p.fetchDataFromPropertyFile("url"));
		String timeouts = System.getProperty("timeouts",p.fetchDataFromPropertyFile("timeouts"));
		wutil.waitForAnElementToLoad(driver, timeouts);
		wutil.maximizeTheWindow(driver);
		wutil.navigateToAppln(driver, url);
		LoginPomPage l = new LoginPomPage(driver);
		l.login(username, password);
	}

	@AfterMethod
	public void logout() {
		Reporter.log("Logout from the  application",true);
		UtilityObject.getTest().log(Status.INFO,"Logout from the  application");
		HomePagePomPage home = new HomePagePomPage(driver);
		wutil.mouseOverUsingActons(driver, home.getAdmin());
		home.getSignout();

	}

	@AfterClass
	public void closeTheBrowser() {
		Reporter.log("Closing the browser",true);
		UtilityObject.getTest().log(Status.INFO,"Closing the browser");
		wutil.closeTheBrowser(driver);
	}

	@AfterTest
	public void closeparallelExecution() {
		Reporter.log("close the parallel Execution",true);
		UtilityObject.getTest().log(Status.INFO,"close the parallel Execution");

	}

	@AfterSuite
	public void DisconnectWithTheDB() throws SQLException {
		Reporter.log("Closed database connection",true);
		UtilityObject.getTest().log(Status.INFO,"Closed database connection");
		dbutil.closeDBConnection();
	}

}
