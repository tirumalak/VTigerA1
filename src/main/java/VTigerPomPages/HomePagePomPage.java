package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePomPage {
	
	@FindBy(partialLinkText="Home")
	private WebElement home_header;
	
	@FindBy(linkText="Contacts")
	private  WebElement contactlink;
	
	@FindBy(linkText="Organizations")
	private  WebElement organizationlink;
	
	@FindBy(xpath="//img[contains(@src,'user')]")
	private WebElement admin;
	
	@FindBy(linkText="Sign Out")
	private WebElement signout;
	
	public HomePagePomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	
	public String getHome_header() {
		return home_header.getText();
	}


	public void getContactlink() {
		 contactlink.click();
	}

	public void getOrganizationlink() {
		 organizationlink.click();
	}

	public WebElement getAdmin() {
		return admin;
	}

	public void getSignout() {
		 signout.click();
	}
	

}
