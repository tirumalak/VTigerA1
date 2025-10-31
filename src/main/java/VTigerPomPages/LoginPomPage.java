package VTigerPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {
	
	//Declaration
	@FindBy(linkText="vtiger")
	private  WebElement vtiger_header;
	
	@FindBy(name="user_name")
	private  WebElement unTF;
	
	@FindBy(name="user_password")
	private  WebElement pswdTF;
	
	@FindBy(id="submitButton")
	private  WebElement loginBtn;
	
	//Initilization
	public LoginPomPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//Utilization

	public String getVtiger_header() {
		return vtiger_header.getText();
	}

	

	

	public void getUnTF(String user) {
		unTF.sendKeys(user);
		
	}

	public void getPswdTF(String password) {
		 pswdTF.sendKeys(password);
	}

	public void getLoginBtn() {
		 loginBtn.click();
	}
	
public void login(String username,String password)
{
	unTF.sendKeys(username);
	 pswdTF.sendKeys(password);
	 loginBtn.click();


	
}
}
