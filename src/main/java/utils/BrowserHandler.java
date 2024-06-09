package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserHandler {
	static WebDriver driver;

	public static WebDriver setDriver(String browserName) {
		if (browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("Internet Explorer")) {
			driver = new InternetExplorerDriver();
		} else {
			return null;
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	

}
