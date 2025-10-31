package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPomPage {

	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement createCon_header;

	@FindBy(name = "lastname")
	private WebElement lastnametf;

	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement org_plusicon;

	@FindBy(id = "search_txt")
	private WebElement searchTf;

	@FindBy(name = "search")
	private WebElement searchBtn;

	@FindBy(linkText = "accountname")
	private WebElement orgname;

	@FindBy(name = "button")
	private WebElement saveBtn;

	@FindBy(name = "support_start_date")
	private WebElement startdatetf;

	@FindBy(name = "support_end_date")
	private WebElement enddatetf;

	public CreateContactPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public String getCreateCon_header() {
		return createCon_header.getText();
	}

	public void getLastnametf(String lastname) {
		lastnametf.sendKeys(lastname);
	}

	public void getOrg_plusicon() {
		org_plusicon.click();
	}

	public void getSearchTf(String orgname) {
		searchTf.sendKeys(orgname);
	}

	public void getSearchBtn() {
		searchBtn.click();
	}

	public void getOrgname() {
		orgname.click();
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

	public void getStartdatetf(String startdate) {
		startdatetf.clear();
		startdatetf.sendKeys(startdate);
	}

	public void getEnddatetf(String enddate) {
		enddatetf.clear();
		enddatetf.sendKeys(enddate);
	}

}
