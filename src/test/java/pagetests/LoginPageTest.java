package pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import basetest.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import utils.PropertyReader;

public class LoginPageTest extends BaseTest {

	LoginPage loginpage;
	HomePage homepage;
	ExtentTest extent;

	@Test(priority = 1, groups = { "smoke", "sanity", "positive" })
	// verifying the initial launch of the instance
	public void launchURL() throws Exception {
		logger = createTest("launching the servicenow instance");
		driver.get(PropertyReader.readKey("url"));
		String title = PropertyReader.readKey("firstPageTitle");
		waitForTitle(title);
		Assert.assertEquals(driver.getTitle(), title);
	}

	@Test(priority = 2, groups = { "sanity", "positive" })
	// verifying the login with valid credentials
	public void testLogin() throws Exception {
		logger = createTest("logging in to the servicenow instance");
		homepage = new LoginPage(driver).login().afterLogin();
		String expectedTitle = PropertyReader.readKey("homePageTitle");
		waitForTitle(expectedTitle);
		String actualTitle = homepage.verifyPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
}
