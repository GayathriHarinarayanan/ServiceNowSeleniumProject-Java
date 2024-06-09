package basepage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BrowserHandler;

public class BasePage {

	public static void enterInput(By by, String input) {
		BrowserHandler.getDriver().findElement(by).sendKeys(input);
	}

	public static void clickElement(By by) {
		BrowserHandler.getDriver().findElement(by).click();
	}
	
	public static Select findDropDown(By by) {
		return new Select(BrowserHandler.getDriver().findElement(by));
	}
	
	public static void presenceOfElement(By by) {
		new WebDriverWait(BrowserHandler.getDriver(),Duration.ofSeconds(2)).until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
