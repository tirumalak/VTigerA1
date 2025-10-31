package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyContactPomPage {
	

	@FindBy(linkText="Contacts")
	private WebElement contacts_header;
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	private WebElement contactHeader;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement verlastname;
	
	@FindBy(xpath="//td[@id='mouseArea_Organization Name']/a")
	private WebElement organizationHeader;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement verStartdate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement verEnddate;
	
	
	public VerifyContactPomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}

	

	public String getContacts_header() {
		return contacts_header.getText();
	}



	public String getContactHeader() {
		return contactHeader.getText();
	}

	
	public String getVerlastname() {
		return verlastname.getText();
	}



	public String getOrganizationHeader() {
		return organizationHeader.getText();
	}

	public String getVerStartdate() {
		return verStartdate.getText();
	}

	public String getVerEnddate() {
		return verEnddate.getText();
	}
	
	

}
