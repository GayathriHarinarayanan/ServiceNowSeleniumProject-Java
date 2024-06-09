package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowRootHandler {
	
	public static SearchContext getElementFromShadowRoot(WebDriver driver, String css) {
		WebElement root =driver.findElement(By.cssSelector(css));
		SearchContext shadow = root.getShadowRoot();
		return shadow;
	}
	
	public static SearchContext getElementFromShadowRoot(SearchContext shadow, String css) {
		WebElement root =shadow.findElement(By.cssSelector(css));
		SearchContext shadow1 = root.getShadowRoot();
		return shadow1;
	}

}
