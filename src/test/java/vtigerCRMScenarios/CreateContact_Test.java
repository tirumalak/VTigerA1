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
import VTigerPomPages.HomePagePomPage;
import VTigerPomPages.VerifyContactPomPage;

public class CreateContact_Test  extends BaseClass{
	@Test
	public void CreateCont_Test() throws Exception
	{
				JavaUtility jutil=new JavaUtility();
				int randomint = jutil.fetchRandomNumber();
				
				
				//fetch the data from excel
				
				ExcelUtility eprop=new ExcelUtility();
				String lastname=eprop.FetchDataFromExcelFile("contacts",1,2)+randomint;
				


				
				
				//click on contact tab
				HomePagePomPage home=new HomePagePomPage(driver);
				SoftAssert soft=new SoftAssert();
				soft.assertEquals(home.getHome_header(), "Home");
				home.getContactlink();
				
				//click on plus icon
				ContactPlusPomPage conplus=new ContactPlusPomPage(driver);
				conplus.getContactPlus();
				
				Thread.sleep(3000);
				//identify last name and enter data and save
				CreateContactPomPage createcon=new CreateContactPomPage(driver);
				createcon.getLastnametf(lastname);
				
				
				Thread.sleep(3000);
				//identify save button and click on it
				createcon.getSaveBtn();
				
				//verify the create contact
				VerifyContactPomPage vercon=new VerifyContactPomPage(driver);
				
				
				String verlastname = vercon.getVerlastname();
				Assert.assertEquals(verlastname, lastname);
				
				//Click on contact tab
				home.getContactlink();
				
				
				//Identify delete button and click on it
				driver.findElement(By.xpath("//a[text()='"+lastname+"']/../../descendant::a[text()='del']")).click();	
				Thread.sleep(3000);
				
				
				
				//close the excel
				eprop.CloseTheExcel();
				soft.assertAll();
	}

}
