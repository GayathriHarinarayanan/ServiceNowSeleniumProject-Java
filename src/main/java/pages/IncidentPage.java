package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import basepage.BasePage;
import utils.PropertyReader;
import utils.ScreenshotCapturer;
import utils.ShadowRootHandler;
import utils.WindowHandler;

public class IncidentPage extends BasePage {
	WebDriver driver;
	SearchContext shadow;

	By frameId = By.cssSelector("#gsft_main");
	By caller = By.id("sys_display.incident.caller_id");
	By userLookUp = By.id("lookup.incident.caller_id");
	By searchUser = By.id("sys_user_table_header_search_control");
	By userBreadCrumb = By.cssSelector("span#sys_user_breadcrumb > a:nth-child(3)");
	By tableRow = By.cssSelector("table#sys_user_table > tbody.list2_body");
	By categoryDropDown = By.id("incident.category");
	By subCategoryDropDown = By.id("incident.subcategory");
	By channelType = By.id("incident.contact_type");
	By impactType = By.id("incident.impact");
	By urgencyType = By.id("incident.urgency");
	By groupLookUp = By.id("lookup.incident.assignment_group");
	By assignToLookUp = By.id("lookup.incident.assigned_to");
	By searchGroup = By.id("sys_user_group_table_header_search_control");
	By groupBreadCrumb = By.cssSelector("span#sys_user_group_breadcrumb > a:nth-child(3)");
	By groupRow = By.cssSelector("table#sys_user_group_table > tbody.list2_body");
	By shortDescription = By.id("incident.short_description");
	By submitButton = By.id("sysverb_insert");

	public IncidentPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean createIncident() throws Exception {
		this.switchToFrame();
		if (this.lookUp(PropertyReader.readKey("incidentCaller"), userLookUp, searchUser, userBreadCrumb, tableRow)) {
			this.chooseCategory(PropertyReader.readKey("category"));
			this.chooseSubCategory(PropertyReader.readKey("subCategory"));
			this.chooseChannel(PropertyReader.readKey("channel"));
			this.chooseImpact(PropertyReader.readKey("impact"));
			this.chooseUrgency(PropertyReader.readKey("urgency"));
			this.lookUp(PropertyReader.readKey("assignmentGroup"), groupLookUp, searchGroup, groupBreadCrumb, groupRow);
			this.lookUp(PropertyReader.readKey("assignTo"), assignToLookUp, searchUser, userBreadCrumb, tableRow);
			enterInput(shortDescription, PropertyReader.readKey("shortdes"));
			return true;
		} else {
			System.out.println("caller not found");
			return false;
		}
	}

	public boolean lookUp(String input, By lookup, By search, By breadcrumb, By table) {
		String parentHandle = driver.getWindowHandle();
		boolean flag = false;
		clickElement(lookup);
		driver.switchTo().window(WindowHandler.getHandle(driver, parentHandle));
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(search), input).sendKeys(Keys.ENTER).perform();
		presenceOfElement(breadcrumb);
		int rowCount = driver.findElement(table).findElements(By.tagName("tr")).size();
		if (rowCount >= 1) {
			try {
				driver.findElement(By.linkText(input)).click();
				flag=true;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				driver.close();
			} finally {
				driver.switchTo().window(parentHandle);
				this.switchToFrame();
			}
		}
		return flag;
	}

	public boolean chooseCategory(String category) {
		try {
			findDropDown(categoryDropDown).selectByVisibleText(category);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean chooseSubCategory(String subCategory) {
		try {
			findDropDown(subCategoryDropDown).selectByVisibleText(subCategory);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean chooseChannel(String channel) {
		try {
			findDropDown(channelType).selectByVisibleText(channel);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean chooseImpact(String impact) {
		impact = impact.toLowerCase();
		if (impact.equals("low")) {
			impact = "3 - Low";
		} else if (impact.equals("medium")) {
			impact = "2 - Medium";
		} else if (impact.equals("high")) {
			impact = "1 - High";
		}

		try {
			findDropDown(impactType).selectByVisibleText(impact);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public boolean chooseUrgency(String urgency) {
		urgency = urgency.toLowerCase();
		if (urgency.equals("low")) {
			urgency = "3 - Low";
		} else if (urgency.equals("medium")) {
			urgency = "2 - Medium";
		} else if (urgency.equals("high")) {
			urgency = "1 - High";
		}

		try {
			findDropDown(urgencyType).selectByVisibleText(urgency);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public void submitIncident() {
		clickElement(submitButton);
	}

	public void switchToFrame() {
		shadow = ShadowRootHandler.getElementFromShadowRoot(driver, "macroponent-f51912f4c700201072b211d4d8c26010");
		WebElement frame = shadow.findElement(frameId);
		driver.switchTo().frame(frame);
	}

}
