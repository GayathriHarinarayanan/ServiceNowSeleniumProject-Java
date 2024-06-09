package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import basepage.BasePage;
import utils.PropertyReader;

public class LoginPage extends BasePage {

	By username =By.id("user_name");
	By password = By.id("user_password");
	By loginButton = By.id("sysverb_login");
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage login() throws Exception {
		enterInput(username,PropertyReader.readKey("username"));
		enterInput(password,PropertyReader.readKey("password"));
		clickElement(loginButton);
		return this;
	}

	public HomePage afterLogin() {
		return new HomePage(driver);
	}

}
