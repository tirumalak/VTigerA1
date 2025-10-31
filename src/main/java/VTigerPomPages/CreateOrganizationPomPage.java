package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPomPage {
	
	
	@FindBy(xpath="//span[text()='Creating New Organization']")
	private WebElement createOrg_header;
	
	@FindBy(name="accountname")
	private WebElement Orgname;
	
	@FindBy(name="button")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industry;
	
	@FindBy(name="accounttype")
	private WebElement type;
	
	@FindBy(id="phone")
	private WebElement Phno;
	
	public CreateOrganizationPomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}


	public String getCreateOrg_header() {
		return createOrg_header.getText();
	}


	public void getOrgname(String orgname) {
		 Orgname.sendKeys(orgname);
	}

	public void getSaveBtn() {
		 saveBtn.click();
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getType() {
		return type;
	}

	public void getPhno(String phno) {
		 Phno.sendKeys(phno);
	}
	
	

}
