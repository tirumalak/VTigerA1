package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrganizationPomPage {

	@FindBy(linkText = "Organizations")
	private WebElement org_header;

	@FindBy(xpath = "//span[@id='dtlview_Organization Name']")
	private WebElement organizationHeader;

	@FindBy(id = "dtlview_Industry")
	private WebElement verIndustry;

	@FindBy(id = "dtlview_Type")
	private WebElement verType;

	@FindBy(id = "dtlview_Phone")
	private WebElement verPhoneno;

	public VerifyOrganizationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public String getOrg_header() {
		return org_header.getText();
	}

	public String getOrganizationHeader() {
		return organizationHeader.getText();
	}

	public String getVerIndustry() {
		return verIndustry.getText();
	}

	public String getVerType() {
		return verType.getText();
	}

	public String getVerPhoneno() {
		return verPhoneno.getText();
	}

}
