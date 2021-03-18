package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.driverfactory.DriverFactory;

public class LoginPage extends DriverFactory {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * This are all locators for login page
	 */

	private By forgotPasswordLnk = By.xpath("(//a[text() = 'Forgotten Password'])[1]");
	private By registerLink = By.xpath("(//a[text() = 'Register'])[2]");
	private By userName = By.xpath("//input[@id = 'input-email']");
	private By password = By.xpath("//input[@id = 'input-password']");
	private By loginBtn = By.xpath("//input[ @value = 'Login' and @type = 'submit']");

	/*
	 * This are the actions for login page
	 */

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean getLoginPageURL() {
		String loginUrl = driver.getCurrentUrl();
		boolean flag = false;
		if (loginUrl.contains("login")) {
			return flag = true;
		}
		return flag;
	}

	public boolean getForgotPasswordLink() {
		return driver.findElement(forgotPasswordLnk).isDisplayed();
	}

	public RegistrationPage navigateToRegistrationPage() {
		driver.findElement(registerLink).click();
		return new RegistrationPage();
	}
	/*
	 * This method returns the account page object This method is used for login
	 */

	public AccountPage doLogin(String uName, String passWord) {
		driver.findElement(userName).sendKeys(uName);
		driver.findElement(password).sendKeys(passWord);
		driver.findElement(loginBtn).click();
		return new AccountPage(driver);
	}
}
