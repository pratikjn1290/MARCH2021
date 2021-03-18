package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.utils.OpenCartConstants;

public class LoginPageTest extends TestBase {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, OpenCartConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void forgotPasswordlnkTest() {
		Assert.assertTrue(loginPage.getForgotPasswordLink());
	}

	@Test(priority = 3)
	public void urlTest() {
		Assert.assertTrue(loginPage.getLoginPageURL());
	}

	@Test(priority = 4)
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
