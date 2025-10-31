package vtigerCRMScenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import ListenersUtilities.UtilityObject;
import VTigerPomPages.CreateOrganizationPomPage;
import VTigerPomPages.HomePagePomPage;
import VTigerPomPages.OrganizationPlusPomPage;
import VTigerPomPages.VerifyOrganizationPomPage;

@Listeners(ListenersUtilities.Listeners.class)
public class CreateOrganizationModule_Test extends BaseClass {
	@Parameters("browser")
	@Test(groups = "somke", retryAnalyzer = ListenersUtilities.RetryAnalyser.class)
	public void Organization_Test() throws Exception {
		UtilityObject.getTest().log(Status.INFO,"Fetching data from random int");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching data from excel");
		ExcelUtility eprop = new ExcelUtility();
		String OrgName = eprop.FetchDataFromExcelFile("organization", 1, 2) + randomint;
		// click on organization link
		UtilityObject.getTest().log(Status.INFO,"Click on organization tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"Validating the home page using soft assert");
		home.getOrganizationlink();

		// click on plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		OrganizationPlusPomPage orgplus = new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();

		// Enter organization name and save it
		UtilityObject.getTest().log(Status.INFO,"Enter organization name save");
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);
		createorg.getSaveBtn();

		// verify the created organization
		VerifyOrganizationPomPage verorg = new VerifyOrganizationPomPage(driver);

		String verifyorg = verorg.getOrganizationHeader();
		Assert.assertEquals(verifyorg, OrgName);
		UtilityObject.getTest().log(Status.PASS,"Validating organization name");

		// click on organizations tab
		UtilityObject.getTest().log(Status.INFO,"Click on organization tab");
		home.getOrganizationlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the organization");
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(3000);
		// Handle the popup
		UtilityObject.getTest().log(Status.INFO,"Handle the popup");
		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Close the excel");
		eprop.CloseTheExcel();
		soft.assertAll();
	}

	@Test(groups = "regreesion")
	public void CreateOrgWithIndAndType_Test() throws Exception {

		// fetch random date and concatenate with name
		UtilityObject.getTest().log(Status.INFO,"Fetching data from random int");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching data from excel");
		ExcelUtility eprop = new ExcelUtility();
		String OrgName = eprop.FetchDataFromExcelFile("organization", 7, 2) + randomint;
		String Industry = eprop.FetchDataFromExcelFile("organization", 7, 3);
		String Type = eprop.FetchDataFromExcelFile("organization", 7, 4);

		// click on organization link
		UtilityObject.getTest().log(Status.INFO,"Clcik on organization tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"Validating home page using soft assert");
		home.getOrganizationlink();

		// click on plus icon
		
		UtilityObject.getTest().log(Status.INFO,"Cl;ick on plus icon");
		OrganizationPlusPomPage orgplus = new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();

		// Enter organization name and save it
		UtilityObject.getTest().log(Status.INFO,"Entetr the org name");
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);

		// Handle Industry and Type DropDown
		UtilityObject.getTest().log(Status.INFO,"Handel the industry dropdown");
		WebElement ind = createorg.getIndustry();
//		Select s=new Select(ind);

		// s.selectByValue(Industry);
		wutil.selectDropDownByValue(ind, Industry);
		WebElement type = createorg.getType();
//		Select ts=new Select(type);
//		ts.selectByValue(Type);
		UtilityObject.getTest().log(Status.INFO,"Handel the type dropdown");
		wutil.selectDropDownByValue(type, Type);

		createorg.getSaveBtn();

		// verify the created organization
		VerifyOrganizationPomPage verorg = new VerifyOrganizationPomPage(driver);

		String header = verorg.getOrganizationHeader();
		Assert.assertEquals(header, OrgName);
		UtilityObject.getTest().log(Status.PASS,"Validating org name by hard assert");

		// verify ind name

		String indus = verorg.getVerIndustry();
		Assert.assertEquals(indus, Industry);
		UtilityObject.getTest().log(Status.PASS,"Validating industry by hard assert");

		// verify org type
		String orgtype = verorg.getVerType();
		Assert.assertEquals(orgtype, Type);
		UtilityObject.getTest().log(Status.PASS,"Validating type by using hard assert");

		// click on organizations tab
		UtilityObject.getTest().log(Status.INFO,"Click on organization tab");
		home.getOrganizationlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the organization");
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(3000);
		// Handle the popup
		UtilityObject.getTest().log(Status.INFO,"Handele the popup");
		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Close the excel");
		eprop.CloseTheExcel();
		soft.assertAll();
	}

	@Test(groups = "regreesion")
	public void OrgannizationWithPhonenumber_Test() throws Exception {

		// fetch random date and concatenate with name
		UtilityObject.getTest().log(Status.INFO,"Fetching data from random int");
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel
		UtilityObject.getTest().log(Status.INFO,"Fetching data from Excel");

		ExcelUtility eprop = new ExcelUtility();
		String OrgName = eprop.FetchDataFromExcelFile("organization", 4, 2) + randomint;
		String Phno = eprop.FetchDataFromExcelFile("organization", 4, 3);
		System.out.println(Phno);

		// click on orgnization link
		UtilityObject.getTest().log(Status.INFO,"Click on orgnaization tab");
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		UtilityObject.getTest().log(Status.PASS,"Validating home page using soft assert");
		home.getOrganizationlink();

		// clik on plus icon
		UtilityObject.getTest().log(Status.INFO,"Click on plus icon");
		OrganizationPlusPomPage orgplus = new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();

		// Enter organization name and save it
		UtilityObject.getTest().log(Status.INFO,"Enter organizaton name and phno and save");
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);
		createorg.getPhno(Phno);
		createorg.getSaveBtn();

		// verify the created organization
		VerifyOrganizationPomPage verorg = new VerifyOrganizationPomPage(driver);

		String header = verorg.getOrganizationHeader();
		Assert.assertEquals(header, OrgName);
		UtilityObject.getTest().log(Status.PASS,"Validating org name using hard assert");

		// verify the phone num text field
		String verPhno = verorg.getVerPhoneno();
		System.out.println(verPhno);
		Assert.assertEquals(verPhno,Phno);
		UtilityObject.getTest().log(Status.PASS,"Validating ph no using hard assert");

		// click on organizations tab
		UtilityObject.getTest().log(Status.INFO,"Click on organization tab");
		home.getOrganizationlink();

		// Identify delete button and click on it
		UtilityObject.getTest().log(Status.INFO,"Delete the organization");
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(3000);
		// Handle the popup
		UtilityObject.getTest().log(Status.INFO,"Handeleing the popup");
		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		UtilityObject.getTest().log(Status.INFO,"Close the excel");
		eprop.CloseTheExcel();
		soft.assertAll();
	}

}
