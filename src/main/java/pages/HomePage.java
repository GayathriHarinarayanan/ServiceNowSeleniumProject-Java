package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.PropertyReader;
import utils.ShadowRootHandler;

public class HomePage {
	WebDriver driver;
	SearchContext shadow;

	By allmenu = By.cssSelector("#d6e462a5c3533010cbd77096e940dd8c");
	By filter = By.cssSelector("#filter");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String verifyPageTitle() {
		return driver.getTitle();
	}

	public HomePage clickAllMenu() {
		shadow = ShadowRootHandler.getElementFromShadowRoot(driver, "macroponent-f51912f4c700201072b211d4d8c26010");
		shadow = ShadowRootHandler.getElementFromShadowRoot(shadow,
				"div > sn-canvas-appshell-root > sn-canvas-appshell-layout > sn-polaris-layout");
		shadow = ShadowRootHandler.getElementFromShadowRoot(shadow,
				"div.sn-polaris-layout.polaris-enabled > div.layout-main > div.header-bar >sn-polaris-header");
		shadow.findElement(allmenu).click();
		return this;
	}

	public IncidentPage searchTheFilter() throws InterruptedException {
		shadow = ShadowRootHandler.getElementFromShadowRoot(shadow, "nav > div > sn-polaris-menu:nth-child(1)");
		Actions act = new Actions(driver);
		act.sendKeys((WebElement)shadow.findElement(filter),"incident.form").sendKeys(Keys.ENTER).perform();
		return new IncidentPage(driver);
	}
}
