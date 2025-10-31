package vtigerCRMScenarios;

import org.openqa.selenium.By;
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

public class CreateOrganization_Test  extends BaseClass{
	@Test
	public void Organization_Test() throws Exception {

		JavaUtility jutil=new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		//fetch the data from excel
		
		ExcelUtility eprop=new ExcelUtility();
		String OrgName=eprop.FetchDataFromExcelFile("organization",1,2)+randomint;
			//click on organization link
						HomePagePomPage home=new HomePagePomPage(driver);
						SoftAssert soft=new SoftAssert();
						soft.assertEquals(home.getHome_header(), "Home");
						home.getOrganizationlink();
						
						//click on plus icon
						OrganizationPlusPomPage orgplus=new OrganizationPlusPomPage(driver);
						orgplus.getOrganizationPlus();
						
						//Enter organization name and save it
						CreateOrganizationPomPage createorg=new CreateOrganizationPomPage(driver);
						createorg.getOrgname(OrgName);
						createorg.getSaveBtn();
						
						//verify the created organization
						VerifyOrganizationPomPage verorg=new VerifyOrganizationPomPage(driver);
						
						String verifyorg =verorg.getOrganizationHeader();
						Assert.assertEquals(verifyorg, OrgName);
						
		//click on organizations tab
						home.getOrganizationlink();
		
		
		//Identify delete button and click on it
		driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../descendant::a[text()='del']")).click();
		Thread.sleep(3000);
		//Handle the popup
		wutil.handleAlertPopupAndClick(driver);
			
			//close the excel
			eprop.CloseTheExcel();
			soft.assertAll();
	}

}
