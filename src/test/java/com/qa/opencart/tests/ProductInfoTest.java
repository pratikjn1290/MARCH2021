package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;

public class ProductInfoTest extends TestBase {

	@BeforeClass
	public void productInfoSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void verifyProductInfoTest() {
		accountPage.searchProduct("mac");
		productSearchPage.searchProductList("Imac");
		Map<String, String> hm = productInfoPage.productInfo();
		System.out.println(hm);

		Assert.assertEquals(hm.get("productName"), "iMac");
		Assert.assertEquals(hm.get("Brand"), "Apple");
		Assert.assertEquals(hm.get("Product Code"), "Product 14");
		Assert.assertEquals(hm.get("Availability"), "Out Of Stock");
		Assert.assertEquals(hm.get("price"), "$100.00");
		Assert.assertEquals(hm.get("exPrice"), "$100.00");
		Assert.assertTrue(productInfoPage.selectQuantityandAddToCart("1"));
	}
}