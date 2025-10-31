package vtigerCRMScenarios;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseclassUtility.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import VTigerPomPages.CreateOrganizationPomPage;
import VTigerPomPages.HomePagePomPage;
import VTigerPomPages.OrganizationPlusPomPage;
import VTigerPomPages.VerifyOrganizationPomPage;

public class CreateOrganizationWithIndustryAndType_Test extends BaseClass {
	@Test
	public void CreateOrgWithIndAndType_Test() throws Exception {


		// fetch random date and concatenate with name
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		// fetch the data from excel

		ExcelUtility eprop = new ExcelUtility();
		String OrgName = eprop.FetchDataFromExcelFile("organization", 7, 2) + randomint;
		String Industry = eprop.FetchDataFromExcelFile("organization", 7, 3);
		String Type = eprop.FetchDataFromExcelFile("organization", 7, 4);

		
		// click on organization link
		HomePagePomPage home = new HomePagePomPage(driver);
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		home.getOrganizationlink();

		// click on plus icon
		OrganizationPlusPomPage orgplus = new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();

		// Enter organization name and save it
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);

		// Handle Industry and Type DropDown

		WebElement ind = createorg.getIndustry();
//		Select s=new Select(ind);

		// s.selectByValue(Industry);
		wutil.selectDropDownByValue(ind, Industry);
		WebElement type = createorg.getType();
//		Select ts=new Select(type);
//		ts.selectByValue(Type);
		wutil.selectDropDownByValue(type, Type);

		createorg.getSaveBtn();

		// verify the created organization
		VerifyOrganizationPomPage verorg = new VerifyOrganizationPomPage(driver);

		String header = verorg.getOrganizationHeader();
		Assert.assertEquals(header, OrgName);
		

		// verify ind name

		String indus = verorg.getVerIndustry();
		Assert.assertEquals(indus, Industry);
		

		// verify org type
		String orgtype = verorg.getVerType();
		Assert.assertEquals(orgtype, Type);
		

		// click on organizations tab
		home.getOrganizationlink();

		// Identify delete button and click on it
		driver.findElement(By.xpath("//a[text()='" + OrgName + "']/../../descendant::a[text()='del']")).click();

		Thread.sleep(3000);
		//Handle the popup
		wutil.handleAlertPopupAndClick(driver);

		// close the excel
		eprop.CloseTheExcel();
		soft.assertAll();
	}

}
