package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ProductSearchPage;

public class TestBase extends DriverFactory {

	public DriverFactory driverFactory;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public ProductSearchPage productSearchPage;
	public ProductInfoPage productInfoPage;
	public Properties prop;
	public WebDriver driver;

	@BeforeTest
	public void setupBrowser() {
		driverFactory = new DriverFactory();
		prop = driverFactory.initProperties();
		driver = initBrowser(prop.getProperty("browser"));
		driver.get(prop.getProperty("url").trim());
		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
		productSearchPage = new ProductSearchPage(driver);
		productInfoPage = new ProductInfoPage(driver);

	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
