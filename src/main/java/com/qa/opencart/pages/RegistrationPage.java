package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.utils.OpenCartConstants;

public class RegistrationPage extends DriverFactory {

	private WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	private By firstName = By.xpath("//input[@id = 'input-firstname']");
	private By lastName = By.xpath("//input[@id = 'input-lastname']");

	private By breadCrumb = By.xpath("//ul[@class = 'breadcrumb']//a[text() = 'Register']");
	private By eMail = By.xpath("//input[@id = 'input-email']");
	private By telePhone = By.xpath("//input[@id = 'input-telephone']");
	private By passWord = By.xpath("//input[@id = 'input-password']");
	private By confirmPassword = By.xpath("//input[@id = 'input-confirm']");
	private By terms = By.xpath("//input[@type = 'checkbox' and @name = 'agree']");
	private By yesCheckBox = By.xpath("(//input[@type = 'radio' and @name = 'newsletter'])[1]");
	private By noCheckBox = By.xpath("(//input[@type = 'radio' and @name = 'newsletter'])[2]");
	private By submit = By.xpath("//input[@type = 'submit' and @value = 'Continue']");
	private By successMsg = By.xpath("//div[@id = 'content']/h1");
	private By logOut = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public String getRegistrationPageTitle() {
		return driver.getTitle();
	}

	public String getRegistrationPageBreadCrumb() {
		return driver.findElement(breadCrumb).getText();
	}

	public boolean getCurrentRegistrationURL() {
		String url = driver.getCurrentUrl();
		if(url.contains("register")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean registerUser(String fName, String lName, String email, String number, String pword,
			String subscribe) {
		driver.findElement(firstName).sendKeys(fName);
		driver.findElement(lastName).sendKeys(lName);
		driver.findElement(eMail).sendKeys(email);
		driver.findElement(telePhone).sendKeys(number);
		driver.findElement(passWord).sendKeys(pword);
		driver.findElement(confirmPassword).sendKeys(pword);

		if (subscribe.equals("Yes")) {
			driver.findElement(yesCheckBox).click();
		} else {
			driver.findElement(noCheckBox).click();
		}

		driver.findElement(terms).click();
		driver.findElement(submit).click();

		String msg = driver.findElement(successMsg).getText();
		if (msg.equals(OpenCartConstants.REGISTRATION_SUCCESS_MESSAGE)) {
			driver.findElement(logOut).click();
			driver.findElement(registerLink).click();
			return true;
		} else {
			return false;
		}

	}

}
