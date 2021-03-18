package com.qa.opencart.utils;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getWebElements(By locator) {
		return driver.findElements(locator);
	}

	public void doClick(By locator) {
		getWebElement(locator).click();
	}

	public void doSendKeys(By locator, String text) {
		getWebElement(locator).sendKeys(text);
	}

	// *****************************************************Explicit Wait Generic Methods*******************************

	public List<WebElement> waitForPresenceofElements(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForVisiblityOfElement(By locator, int duration)

	{
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementTobeClick(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, int duration) {
		WebElement elem = waitForElementTobeClick(locator, duration);
		elem.click();

	}

	public Alert waitForAlertToBePresent(int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlertWhenPresent(int duration) {
		waitForAlertToBePresent(duration).accept();

	}

	public void dismissAlertWhenPresent(int duration) {
		waitForAlertToBePresent(duration).dismiss();
	}

	public WebElement waitForVisibilityofElement(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForVisibilityOfAllElements(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Boolean waitforTextToBePresent(By locator, String text, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.textToBe(locator, text));
	}

	public Boolean waitForTextToBePresentInWebElement(By locator, String text, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	public Boolean waitFortextToBePresentInElementValue(By locator, String text, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		return wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
	}

}
