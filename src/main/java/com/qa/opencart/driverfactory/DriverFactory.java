package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());

		}

		else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Select valid browser :" + browserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {
		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/configs/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/configs/config_qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/configs/config_stagging.properties");
					break;
				default:
					System.out.println("Please pass the right env value...");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
