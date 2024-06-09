package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
	
	public static String getHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	public static String getHandle(WebDriver driver, String parent) {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parent)) {
				return handle;
			}
		}
		return null;
	}

}
