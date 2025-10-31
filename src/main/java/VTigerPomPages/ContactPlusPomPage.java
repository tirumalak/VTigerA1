package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPlusPomPage {
	

	@FindBy(linkText="Contacts")
	private WebElement contacts_header;
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement contactPlus;
	
	public ContactPlusPomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	

	public String getContacts_header() {
		return contacts_header.getText();
	}


	public void getContactPlus() {
		 contactPlus.click();
	}
	
}
