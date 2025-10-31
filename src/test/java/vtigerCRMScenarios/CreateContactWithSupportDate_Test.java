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

public class CreateContactWithSupportDate_Test  extends BaseClass{
	@Test
	public void ContactWithSupportDate_Test() throws Exception
	{
		

		
		
		//fetch random date and concatenate with name
		JavaUtility jutil=new JavaUtility();
		int randomint = jutil.fetchRandomNumber();

		//fetch the data from excel
		
		ExcelUtility eprop=new ExcelUtility();
		String lastname=eprop.FetchDataFromExcelFile("contacts",4,2)+randomint;;
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
		
		//enter support start date
//		Date d=new Date();
//		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
//		String startdate = sim.format(d);
	
		String startdate = jutil.fetchCurrentDate();
		  System.out.println(startdate);
		
		//identify support start date
		  createcon.getStartdatetf(startdate);
		  
		
		
//		//enter support end date
//		Calendar c=sim.getCalendar();
//		c.add(Calendar.DAY_OF_MONTH, 30);
//		String enddate = sim.format(c.getTime());
		
		
		 String enddate = jutil.fetchDateAfterGivenDays(30);
		System.out.println(enddate);
		
		//identify support end date
		createcon.getEnddatetf(enddate);
		
		//identify save button and click on it
		createcon.getSaveBtn();
		
		//verify the create contact
		VerifyContactPomPage vercon=new VerifyContactPomPage(driver);
		
		
		String verlastname =vercon.getVerlastname();
		Assert.assertEquals(verlastname, lastname);
		
		
		Thread.sleep(3000);
		
		//verify support start date
		
		String verstdate =vercon.getVerStartdate();
		Assert.assertEquals(verstdate, startdate);
		
		
		//verify support end date
		
		 String verenddate =vercon.getVerEnddate();
		 Assert.assertEquals(verenddate, enddate);
		
		
		//Click on contact tab
		home.getContactlink();
		
				
		//Identify delete button and click on it
		driver.findElement(By.xpath("//a[text()='"+lastname+"']/../../descendant::a[text()='del']")).click();
		
		Thread.sleep(3000);
		// driver.switchTo().alert().accept();
		wutil.handleAlertPopupAndClick(driver);
		//close the excel
		eprop.CloseTheExcel();
		soft.assertAll();
		
	}

}
