package pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import pages.HomePage;
import utils.BrowserHandler;

public class HomePageTest extends BaseTest {

	HomePage homePage;

	@Test(priority = 3, groups = { "positive" })
	// clicking on the All menu from the banner
	public void testallMenu() throws Exception {
		logger = createTest("clicking on All Menu");
		homePage = new HomePage(BrowserHandler.getDriver());
		homePage.clickAllMenu();
	}

	@Test(priority = 4, groups = { "positive" })
	// searching the filter navigator to open new incident form
	public void testFilterOfNavigator() throws InterruptedException {
		logger = createTest("opening new incident form");
		homePage.searchTheFilter();
		waitForTitleContains("Incident");
		Assert.assertTrue(driver.getTitle().startsWith("Create INC"));
	}

}
