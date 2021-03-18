package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_Browser(String browserName) {

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
			System.out.println("Select valid browser name :" + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {
		prop = new Properties();
		FileInputStream fis = null;
		File file = null;
		String env = System.getProperty("env");

		if (env == null) {
			file = new File("./src/test/resources/configs/config.properties");
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					file = new File("./src/test/resources/configs/config_qa.properties");
					break;
				case "stagging":
					file = new File("./src/test/resources/configs/config_stagging.properties");
					break;

				default:
					System.out.println("Invalid environment type");
					break;
				}
				fis = new FileInputStream(file);
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;

	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
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
