package vtigerCRMScenarios;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;

import GenericUtilities.WebDriverUtility;
import ListenersUtilities.UtilityObject;
import VTigerPomPages.ContactPlusPomPage;
import VTigerPomPages.CreateContactPomPage;
import VTigerPomPages.CreateOrganizationPomPage;
import VTigerPomPages.HomePagePomPage;

import VTigerPomPages.OrganizationPlusPomPage;
import VTigerPomPages.VerifyContactPomPage;
import VTigerPomPages.VerifyOrganizationPomPage;

@Listeners(ListenersUtilities.Listeners.class)
public class CreateContactModule_Test extends BaseClass {
	
	@Test(groups = "somke", retryAnalyzer = ListenersUtilities.RetryAnalyser.class)
	public void CreateCont_Test() throws Exception {

		System.out.println("errorts");
		// fetch random date and concatenate with name
		UtilityObject.getTest().log(Status.INFO,"Fetching data from random int");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching data from excel ");
		ExcelUtility eprop = new ExcelUtility();
		String lastname = eprop.FetchDataFromExcelFile("contacts", 1, 2) + randomint;

		WebDriverUtility wutil = new WebDriverUtility();

		// click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		//Validate homepage using soft asserrt
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"Validation Home page uisng soft assert");
		home.getContactlink();

		// click on plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		ContactPlusPomPage conplus = new ContactPlusPomPage(driver);
		conplus.getContactPlus();
		
		Thread.sleep(3000);
		// identify last name and enter data and save
		UtilityObject.getTest().log(Status.INFO,"Enter the last name and save");
		CreateContactPomPage createcon = new CreateContactPomPage(driver);
		createcon.getLastnametf(lastname);

		Thread.sleep(3000);
		
		createcon.getSaveBtn();

		// verify the create contact
		
		VerifyContactPomPage vercon = new VerifyContactPomPage(driver);

		String verlastname = vercon.getVerlastname();
		Assert.assertEquals(verlastname, lastname,"Contact name verification failed");
		UtilityObject.getTest().log(Status.PASS,"Validated Contact name  using hard assert");
		
		// Click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact atb");
		home.getContactlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the contact");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(3000);

		// Handle the popup
		// driver.switchTo().alert().accept();
		UtilityObject.getTest().log(Status.INFO,"Handeled the popup");
		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Closed the excel");
		eprop.CloseTheExcel();
		soft.assertAll();
	}

	@Test(groups = "regreesion")
	public void CreateContactWithOrg_Test() throws Exception {

		// fetch random date and concatenate with name
		UtilityObject.getTest().log(Status.INFO,"Fetch the data from randomint");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching the data from excel");
		ExcelUtility eprop = new ExcelUtility();
		String lastname = eprop.FetchDataFromExcelFile("contacts", 7, 3) + randomint;
		String OrgName = eprop.FetchDataFromExcelFile("organization", 7, 2) + randomint;

		// click on organization link
		UtilityObject.getTest().log(Status.INFO,"Clicking on Organization tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"V/alidating the home page using soft assert");
		home.getOrganizationlink();

		// click on plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		OrganizationPlusPomPage orgplus = new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();

		// Enter organization name and save it
		UtilityObject.getTest().log(Status.INFO,"Enter organization name and save");
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);
		createorg.getSaveBtn();
		// verify the created organization
		VerifyOrganizationPomPage verorg = new VerifyOrganizationPomPage(driver);

		String verifyorg = verorg.getOrganizationHeader();
		Assert.assertEquals(verifyorg, OrgName);
		UtilityObject.getTest().log(Status.PASS,"VAlidating the organization name using hard assert");
		// click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact tab");
		home.getContactlink();
		// click on contact plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		ContactPlusPomPage conplus = new ContactPlusPomPage(driver);
		conplus.getContactPlus();
		// click on last name tf
		UtilityObject.getTest().log(Status.INFO,"Enter the last name");
		CreateContactPomPage createcon = new CreateContactPomPage(driver);
		createcon.getLastnametf(lastname);

		// parent window
		UtilityObject.getTest().log(Status.INFO,"Fetch the parent window id");
		String pwid = wutil.fetchParentWindowId(driver);
		// click on org pllus icon
		UtilityObject.getTest().log(Status.INFO,"Click on orgplus icon");
		createcon.getOrg_plusicon();

		// switch to child window based on url and stop the driver control
		UtilityObject.getTest().log(Status.INFO,"Switch to child window");
		wutil.switchWindowUsingUrl(driver, "specific_contact_account_address");
		// identify orgname and click on it where child window will close
		UtilityObject.getTest().log(Status.INFO,"Identify orgnization  name in child window and click on it");
		
		createcon.getSearchTf(OrgName);
		createcon.getSearchBtn();
		driver.findElement(By.linkText(OrgName)).click();

		// switch back to parent window
		// driver.switchTo().window(pwid);
		UtilityObject.getTest().log(Status.INFO,"Swiching back to parent window");
		wutil.switchToParentWindow(driver, pwid);

		// Identify save button and click on it
		UtilityObject.getTest().log(Status.INFO,"Click on save button");
		createcon.getSaveBtn();

		// Verify the created contact
		VerifyContactPomPage vercon = new VerifyContactPomPage(driver);

		String verlastname = vercon.getVerlastname();
		Assert.assertEquals(verlastname, lastname);
		UtilityObject.getTest().log(Status.PASS,"Validating the lastname using hard assert");

		// verify org in cont info page

		String verifyorg1 = vercon.getOrganizationHeader();
		Assert.assertEquals(verifyorg1, OrgName);
		UtilityObject.getTest().log(Status.PASS,"Validating the org name using hard assert");

		// Click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact tab");
		home.getContactlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the contact");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(3000);

		// Handle the popup
		// driver.switchTo().alert().accept();
		UtilityObject.getTest().log(Status.INFO,"Handele the popup");
		wutil.handleAlertPopupAndClick(driver);

		// click on organizations tab
		UtilityObject.getTest().log(Status.INFO,"Click on organization tab");
		home.getOrganizationlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the Organization");
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(3000);

		// Handle the popup
		// driver.switchTo().alert().accept();
		UtilityObject.getTest().log(Status.INFO,"Handele the popup");

		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Close the excel");
		eprop.CloseTheExcel();
		soft.assertAll();
	}

	@Test(groups = "regreesion")
	public void ContactWithSupportDate_Test() throws Exception {

		// fetch random date and concatenate with name
		UtilityObject.getTest().log(Status.INFO,"Fetching the data from randomint");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching the data from excel");
		ExcelUtility eprop = new ExcelUtility();
		String lastname = eprop.FetchDataFromExcelFile("contacts", 4, 2) + randomint;

		// click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"Validating the home page using soft assert");
		home.getContactlink();

		// click on plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		ContactPlusPomPage conplus = new ContactPlusPomPage(driver);
		conplus.getContactPlus();

		Thread.sleep(3000);
		// identify last name and enter data and save
		UtilityObject.getTest().log(Status.INFO,"Enter lastname ");
		CreateContactPomPage createcon = new CreateContactPomPage(driver);
		createcon.getLastnametf(lastname);

		Thread.sleep(3000);

		String startdate = jutil.fetchCurrentDate();
		System.out.println(startdate);

		// identify support start date
		UtilityObject.getTest().log(Status.INFO,"Enter support start date");
		createcon.getStartdatetf(startdate);

//		//enter support end date
//		Calendar c=sim.getCalendar();
//		c.add(Calendar.DAY_OF_MONTH, 30);
//		String enddate = sim.format(c.getTime());

		String enddate = jutil.fetchDateAfterGivenDays(30);
		System.out.println(enddate);

		// identify support end date
		UtilityObject.getTest().log(Status.INFO,"Enter support end date");
		createcon.getEnddatetf(enddate);

		// identify save button and click on it
		UtilityObject.getTest().log(Status.INFO,"Click on save");
		createcon.getSaveBtn();

		// verify the create contact
		VerifyContactPomPage vercon = new VerifyContactPomPage(driver);

		String verlastname = vercon.getVerlastname();
		Assert.assertEquals(verlastname, lastname);
		UtilityObject.getTest().log(Status.PASS,"Validating contact with hard assert");

		Thread.sleep(3000);

		// verify support start date
		
		String verstdate = vercon.getVerStartdate();
		Assert.assertEquals(verstdate, startdate);
		UtilityObject.getTest().log(Status.PASS,"Validating Support start date with hard assert");

		// verify support end date

		String verenddate = vercon.getVerEnddate();
		Assert.assertEquals(verenddate, enddate);
		UtilityObject.getTest().log(Status.PASS,"Validating Support end date with hard assert");

		// Click on contact tab
		UtilityObject.getTest().log(Status.INFO,"Click on contact tab");
		home.getContactlink();
		

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the contact");
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(3000);

		// Handle the popup
		// driver.switchTo().alert().accept();
		UtilityObject.getTest().log(Status.INFO,"Handeled popup");

		wutil.handleAlertPopupAndClick(driver);
		
		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Close the excel");
		eprop.CloseTheExcel();
		soft.assertAll();

	}

}
