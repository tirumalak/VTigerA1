package vtigerCRMScenarios;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseclassUtility.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import VTigerPomPages.ContactPlusPomPage;
import VTigerPomPages.CreateContactPomPage;
import VTigerPomPages.CreateOrganizationPomPage;
import VTigerPomPages.HomePagePomPage;
import VTigerPomPages.OrganizationPlusPomPage;
import VTigerPomPages.VerifyContactPomPage;
import VTigerPomPages.VerifyOrganizationPomPage;

public class CreateContactWithOrganization_Test extends BaseClass{
	@Test
	public void CreateContactWithOrg_Test() throws Exception {

		
		
		//fetch random date and concatenate with name
		JavaUtility jutil=new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		//fetch the data from excel
		
		ExcelUtility eprop=new ExcelUtility();
		String lastname=eprop.FetchDataFromExcelFile("contacts",7,3)+randomint;
		String OrgName=eprop.FetchDataFromExcelFile("organization", 7, 2)+randomint;


		
		
		
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
				
				
				//click on contact tab
				home.getContactlink();
				//click on contact plus icon
				ContactPlusPomPage conplus=new ContactPlusPomPage(driver);
				conplus.getContactPlus();
				//click on last name tf
				CreateContactPomPage createcon=new CreateContactPomPage(driver);
				createcon.getLastnametf(lastname);
			
				
				//parent window
				String pwid = wutil.fetchParentWindowId(driver);
// click on org pllus icon
				createcon.getOrg_plusicon();
				
				
				//switch to child window based on url and stop the driver control
				wutil.switchWindowUsingUrl(driver,"specific_contact_account_address");
				//identify orgname and click on it where child window will close
				createcon.getSearchTf(OrgName);
				createcon.getSearchBtn();
				driver.findElement(By.linkText(OrgName)).click();
			
				
				
				//switch back to parent window
			//	driver.switchTo().window(pwid);
				
				wutil.switchToParentWindow(driver,pwid);
				
				
				//Identify save button and click on it
				createcon.getSaveBtn();
				
				
				//Verify the created contact
				VerifyContactPomPage vercon=new VerifyContactPomPage(driver);
				
				String verlastname =vercon.getVerlastname();
				Assert.assertEquals(verlastname, lastname);
				
				
				//verify org in cont info page
				
				String verifyorg1 =vercon.getOrganizationHeader();
				Assert.assertEquals(verifyorg1, OrgName);
				
				
				//Click on contact tab
				home.getContactlink();
				
				
				//Identify delete button and click on it
				driver.findElement(By.xpath("//a[text()='"+lastname+"']/../../descendant::a[text()='del']")).click();
				Thread.sleep(3000);
				
				//Handle the popup
			//	driver.switchTo().alert().accept();
				wutil.handleAlertPopupAndClick(driver);
				
				

				//click on organizations tab
				home.getOrganizationlink();
				
				//Identify delete button and click on it
				driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../descendant::a[text()='del']")).click();
				Thread.sleep(3000);

//				driver.switchTo().alert().accept();
				wutil.handleAlertPopupAndClick(driver);
				//close the excel
				eprop.CloseTheExcel();
				soft.assertAll();
				
	}

}
