package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPlusPomPage {
	

	@FindBy(linkText="Organizations")
	private WebElement org_header;
	
	@FindBy(xpath="//img[@title=\"Create Organization...\"]")
	private WebElement organizationPlus;
	
	public OrganizationPlusPomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}


	public String getOrg_header() {
		return org_header.getText();
	}

	public void getOrganizationPlus() {
		 organizationPlus.click();
	}
	

}
