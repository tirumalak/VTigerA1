
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

public class CreateOrganizationWithPhonenumber_Test extends BaseClass {
	@Test
	public void OrgannizationWithPhonenumber_Test() throws Exception
	{
		
		
		//fetch random date and concatenate with name
		JavaUtility jutil=new JavaUtility();
		int randomint = jutil.fetchRandomNumber();
		
		//fetch the data from excel
		
		ExcelUtility eprop=new ExcelUtility();
		String OrgName=eprop.FetchDataFromExcelFile("organization", 4, 2)+randomint;
		String Phno=eprop.FetchDataFromExcelFile("organization", 4, 3);
		//click on orgnization link
		HomePagePomPage home=new HomePagePomPage(driver);
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(home.getHome_header(), "Home");
		home.getOrganizationlink();
		
		//clik on plus icon
		OrganizationPlusPomPage orgplus=new OrganizationPlusPomPage(driver);
		orgplus.getOrganizationPlus();
				
				//Enter organization name and save it
		CreateOrganizationPomPage createorg=new CreateOrganizationPomPage(driver);
		createorg.getOrgname(OrgName);
		createorg.getPhno(Phno);
		createorg.getSaveBtn();
				
				//verify the created organization
				VerifyOrganizationPomPage verorg=new VerifyOrganizationPomPage(driver);
				
				String header =verorg.getOrganizationHeader();
				Assert.assertEquals(header, OrgName);
				
//				//verify the phone num text field
//				String verPhno =verorg.getVerPhoneno();
//				Assert.assertEquals(verPhno,Phno);
				
				Thread.sleep(3000);
				//click on organizations tab
				home.getOrganizationlink();
				
				//Identify delete button and click on it
				driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../descendant::a[text()='del']")).click();
				
				Thread.sleep(3000);
				// driver.switchTo().alert().accept();
				wutil.handleAlertPopupAndClick(driver);
				//close the excel
				eprop.CloseTheExcel();
				soft.assertAll();
	}

}
