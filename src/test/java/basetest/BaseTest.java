package basetest;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import utils.ScreenshotCapturer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.BrowserHandler;
import utils.PropertyReader;

public class BaseTest {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static ExtentSparkReporter reporter;
	protected static ExtentReports extent;
	protected static ExtentTest logger;
	protected static String methodMsg;

	static {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmssSSSS").format(new Date());
		String rPath = null;
		try {
			rPath = PropertyReader.readKey("reportPath").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fPath = rPath + "report" + dateName + ".html";
		reporter = new ExtentSparkReporter(fPath);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = BrowserHandler.setDriver("Chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// report configurations
		reporter.config().setDocumentTitle("servicenow test report");
		reporter.config().setReportName("servicenow test execution report");
		reporter.config().setTheme(Theme.DARK);
	}

	 public ExtentTest createTest(String name) {
	        return extent.createTest(name);
	    }
	 
	@AfterMethod
	public void resultCheck(ITestResult result) throws Exception {
		String path=ScreenshotCapturer.getPageScreenshot(driver);
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, "test case passed:" + result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "test case failed:" + result.getName() + " " + methodMsg);	
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, "test case skipped:" + result.getName());
		}
		logger.addScreenCaptureFromPath(path);
	}

	public static void waitForTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));
	}

	public static void waitForTitleContains(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}

	@AfterSuite
	public void tearDown() {
		if (BrowserHandler.getDriver() != null)
			driver.quit();
		extent.flush();
	}
}
