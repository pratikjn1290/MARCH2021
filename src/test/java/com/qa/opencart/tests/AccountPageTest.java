package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.TestBase;
import com.qa.opencart.utils.OpenCartConstants;

public class AccountPageTest extends TestBase {

	@BeforeClass
	public void accountPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accountPageTitleTest() {
		String title = accountPage.getAccountPageTitle();
		Assert.assertEquals(title, OpenCartConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 4)
	public void totalSectionTest() {
		Assert.assertTrue(accountPage.accountPageSectionCount() == 4);
	}

	@Test(priority = 3)
	public void accountPageUrlTest() {
		Assert.assertTrue(accountPage.getAccountPageUrl());
	}

	@Test(priority = 2)
	public void rightSideMenu() {
		Assert.assertTrue(accountPage.getRightSideMenus() > 0);
	}

	@Test(priority = 5)
	public void accountPageLogoTest() {
		Assert.assertTrue(accountPage.accountPageLogo());
	}

	@Test(priority = 6)
	public void accountSectionList() {
		List<String> sections = accountPage.accountSectionList();
		Assert.assertEquals(sections, OpenCartConstants.sectionList());
	}

	@Test(priority = 7)
	public void accountBreadCrumTest() {
		Assert.assertTrue(accountPage.getBreadCrum());
	}

}
