package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.utils.OpenCartConstants;

public class ProductSearchTest extends TestBase {

	@BeforeClass
	public void productSearchSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		productSearchPage = accountPage.searchProduct("mac");

	}

	@Test(priority = 0)
	public void searchProductUrlTest() {
		Assert.assertTrue(productSearchPage.getProductSearchUrl());
	}

	@Test(priority = 1)
	public void searchProductTitleTest() {
		String title = productSearchPage.getProductInfoPageTitle();
		Assert.assertEquals(title, OpenCartConstants.SEARCH_PRODUCT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void searchProductCount() {
		Assert.assertTrue(productSearchPage.searchProductCount() > 0);
	}

	@Test(priority = 3)
	public void breadcrumbTest() {
		Assert.assertTrue(productSearchPage.getBreadCrumb());
	}

	@Test(priority = 4)
	public void searchBoxDisplayedTest() {
		Assert.assertTrue(productSearchPage.searchBoxDisplayed());
	}

}
