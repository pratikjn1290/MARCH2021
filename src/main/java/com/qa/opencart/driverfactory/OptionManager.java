package com.qa.opencart.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {

	private Properties prop;
	private FirefoxOptions fo;
	private ChromeOptions co;

	public OptionManager(Properties prop) {
		this.prop = prop;
	}

	public FirefoxOptions getFirefoxOption() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incognito");
		return fo;
	}
	
	public ChromeOptions getChromeOption() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	}
	
}
