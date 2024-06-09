package pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;
import basetest.BaseTest;
import pages.IncidentPage;
import utils.BrowserHandler;

public class IncidentPageTest extends BaseTest {

	IncidentPage incidentPage;
	boolean incidentFlag;

	@Test(priority = 5, groups = { "positive" })
	// creating a new incident
	public void testCreateNewIncident() throws Exception {
		logger = createTest("filling incident details on the form and validating");
		incidentPage = new IncidentPage(BrowserHandler.getDriver());
		incidentFlag = incidentPage.createIncident();
		if (!incidentFlag) {
			methodMsg = "incident cannot be created; caller not found";
			Assert.fail();
		}
	}

	@Test(priority = 6, groups = { "positive" })
	// creating a new incident
	public void testSubmitIncident() throws Exception {
		logger = createTest("submitting a new incident");
		if (!incidentFlag) {
			methodMsg = ":incident cannot be created;";
			Assert.fail();
		} else {
			incidentPage.submitIncident();
		}
	}

}
