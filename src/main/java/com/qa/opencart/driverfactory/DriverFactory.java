package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	Properties prop;
	OptionManager op;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initBrowser(String browserName, String browserVersion) {
		op = new OptionManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browserName,browserVersion);
			} else {
				tlDriver.set(new ChromeDriver(op.getChromeOption()));
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browserName,browserVersion);
			} else {
				
				tlDriver.set(new FirefoxDriver(op.getFirefoxOption()));
			}

		} else {
			System.out.println("Select valid browser :" + browserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
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

	private void initRemoteDriver(String browser, String bVersion) {
		if (browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, op.getChromeOption());
			cap.setCapability("browsername", browser);
			cap.setCapability("browserversion", bVersion);
			cap.setCapability("enableVNC", true);
			cap.setCapability("enableVideo", false);

			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubURL").trim()),cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, op.getFirefoxOption());
			cap.setCapability("browsername", browser);
			cap.setCapability("enableVNC", true);
			cap.setCapability("enableVideo", false);
			cap.setCapability("browserversion", bVersion);
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubURL").trim()), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
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
