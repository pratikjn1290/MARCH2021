package com.qa.opencart.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

	@BeforeTest
	public void setupBrowser() {
		driverFactory = new DriverFactory();
		driver = init_Browser("chrome");
		prop = driverFactory.init_properties();
		loginPage = new LoginPage(driver);
		accountPage = new AccountPage(driver);
		productSearchPage = new ProductSearchPage(driver);
		productInfoPage = new ProductInfoPage(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
