package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.driverfactory.OptionManager;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ProductSearchPage;

public class TestBase extends DriverFactory {

	public DriverFactory df;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public ProductSearchPage productSearchPage;
	public ProductInfoPage productInfoPage;
	public Properties prop;
	private WebDriver driver;

	@BeforeTest
	@Parameters({"browser","version"})
	public void setupBrowser(String bName, String bVersion) {
		df = new DriverFactory();
		prop = df.initProperties();
		String browserName = prop.getProperty("browser").trim();	
		if(browserName!= null)
		{
			browserName = bName;
		}
		driver = df.initBrowser(browserName, bVersion);
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
