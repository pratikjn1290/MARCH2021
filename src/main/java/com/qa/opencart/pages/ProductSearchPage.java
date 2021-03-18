package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.driverfactory.DriverFactory;

public class ProductSearchPage extends DriverFactory {

	private WebDriver driver;

	public ProductSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	private By searchProductName = By.xpath("//div[@class = 'caption']//a");
	private By searchPageBreadCrumb = By.xpath("//ul[@class = 'breadcrumb']//a[text() = 'Search']");
	private By searchBox = By.cssSelector("#input-search");
	private By noProductMessage = By.xpath("//p[contains(text(), 'There is no product')]");

	public String getProductInfoPageTitle() {
		String title = driver.getTitle();
		String[] arr = title.split("-");
		String pName = arr[0].trim();
		return pName;

	}

	public boolean getProductSearchUrl() {
		boolean flag = false;
		String url = driver.getCurrentUrl();
		if (url.contains("search")) {
			return flag = true;
		}
		return flag;
	}

	public boolean getBreadCrumb() {
		return driver.findElement(searchPageBreadCrumb).isDisplayed();
	}

	public boolean searchBoxDisplayed() {
		return driver.findElement(searchBox).isDisplayed();
	}

	public int searchProductCount() {
		List<WebElement> searchedProduct = driver.findElements(searchProductName);
		return searchedProduct.size();
	}

	public String noSearchProduct() {
		String noMsg = null;
		if (searchProductCount() == 0) {
			noMsg = driver.findElement(noProductMessage).getText();
		}
		return noMsg;
	}

	public ProductInfoPage searchProductList(String pName) {
		List<WebElement> searchProduct = driver.findElements(searchProductName);
		for (WebElement product : searchProduct) {
			if (product.getText().equalsIgnoreCase(pName)) {
				product.click();
				break;
			}
		}

		return new ProductInfoPage(driver);
	}
}