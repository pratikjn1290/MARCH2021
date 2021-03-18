package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.driverfactory.DriverFactory;

public class AccountPage extends DriverFactory {

	private WebDriver driver;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	private By accountPageLogo = By.xpath("//a[text() = 'Your Store']");
	private By accountSection = By.xpath("//div[@id = 'content']/h2");
	private By breadCrum = By.xpath("//a[text() = 'Account']");
	private By accountLink = By.xpath("//div[@class = 'list-group']/a");
	
	public String getAccountPageTitle() {
		return driver.getTitle();
	}

	public boolean getAccountPageUrl() {
		String url = driver.getCurrentUrl();
		boolean flag = false;
		if (url.contains("account")) {
			flag = true;
		}
		return flag;
	}

	public int getRightSideMenus() {
		List<WebElement> links = driver.findElements(accountLink);
		return links.size();

	}

	public int accountPageSectionCount() {
		List<WebElement> sections = driver.findElements(accountSection);
		return sections.size();
	}

	public List<String> accountSectionList() {
		List<WebElement> sections = driver.findElements(accountSection);
		List<String> sectionList = new ArrayList<String>();
		for (WebElement section : sections) {
			sectionList.add(section.getText());
		}
		return sectionList;
	}

	public boolean accountPageLogo() {
		return driver.findElement(accountPageLogo).isDisplayed();
	}

	public boolean getBreadCrum() {
		return driver.findElement(breadCrum).isDisplayed();
	}

	public ProductSearchPage searchProduct(String productName) {
		driver.findElement(By.xpath("//input[@name = 'search' and @placeholder = 'Search']")).sendKeys(productName);
		driver.findElement(By.xpath("//button[@type = 'button' and @class = 'btn btn-default btn-lg']")).click();
		return new ProductSearchPage(driver);
	}

}
