package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotCapturer {
	public static String  getPageScreenshot(WebDriver driver) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSSS").format(new Date());
		String sPath = PropertyReader.readKey("screenshotPath").toString();
		String path = sPath + dateName + ".png";
		FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(path));
		return path;
	}
}
