package GenericUtilities;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {

	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForAnElementToLoad(WebDriver driver, String timeouts) {
		long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void navigateToAppln(WebDriver driver, String url) {
		driver.get(url);
	}

	public void handleAlertPopupAndClick(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void mouseOverUsingActons(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void doubleClickUsingActions(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

	public void clickAndHoldUsingActions(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}

	public void dragAndDropUsingActions(WebDriver driver, WebElement element1, WebElement element2) {
		Actions act = new Actions(driver);
		act.dragAndDrop(element1, element2).perform();
	}

	public void moveToElementUsingActions(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void contextClickUsingActons(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	public void scrollToElementUsingActions(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}

	public void scrollByAmountUsingActions(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}

	public void closeTheBrowser(WebDriver driver) {
		driver.quit();
	}

	public String fetchParentWindowId(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}

	public void switchWindowUsingUrl(WebDriver driver, String exp_url) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(exp_url))
				break;

		}

	}

	public void switchWindowUsingTitle(WebDriver driver, String exp_title) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(exp_title))
				break;

		}

	}

	public void switchToParentWindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}

	public void selectDropDownByValue(WebElement dropdown, String value) {
		Select s = new Select(dropdown);
		s.selectByValue(value);

	}

}
