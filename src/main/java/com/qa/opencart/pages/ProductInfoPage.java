package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.driverfactory.DriverFactory;

public class ProductInfoPage extends DriverFactory {

	private WebDriver driver;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
	}

	private By thumbNailImages = By.xpath("//ul[@class = 'thumbnails']/li/a/img");
	private By productHeader = By.xpath("//div[@class = 'col-sm-4']/h1");
	private By productMetaData = By.cssSelector("#content .col-sm-4 .list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content .col-sm-4 .list-unstyled:nth-of-type(2) li");
	private By quantityCount = By.xpath("//input[@id = 'input-quantity' and @name = 'quantity']");
	private By addToCartButton = By
			.xpath("//button[@id = 'button-cart' and @class = 'btn btn-primary btn-lg btn-block']");
	private By alertMsg = By.xpath("//div[contains(text(), 'Success:')]");

	public int getThumbNailCount() {

		List<WebElement> elem = driver.findElements(thumbNailImages);
		return elem.size();
	}

	public Map<String, String> productInfo() {
		Map<String, String> productInfoData = new HashMap<String, String>();
		productInfoData.put("productName", driver.findElement(productHeader).getText());
		List<WebElement> datas = driver.findElements(productMetaData);
		for (WebElement data : datas) {
			productInfoData.put(data.getText().split(":")[0].trim(), data.getText().split(":")[1].trim());
		}

		List<WebElement> productPriceList = driver.findElements(productPrice);
		productInfoData.put("price", productPriceList.get(0).getText().trim());
		productInfoData.put("exPrice", productPriceList.get(1).getText().split(":")[1].trim());

		return productInfoData;
	}

	public boolean selectQuantityandAddToCart(String quantity) {
		driver.findElement(quantityCount).clear();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(quantityCount).sendKeys(quantity);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(addToCartButton))).click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(alertMsg));
		String msg = driver.findElement(alertMsg).getText();
		if (msg.trim().contains("Success")) {
			return true;
		} else {
			return false;
		}
	}
}
