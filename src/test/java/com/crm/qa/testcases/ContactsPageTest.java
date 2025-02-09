package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;



public class ContactsPageTest extends TestBase{
	ContactsPage contactpage;
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	
	static String  sheetname = "contacts";
	
	ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		 loginpage = new LoginPage();
		 testutil = new TestUtil();
		  contactpage = new ContactsPage();
		homepage =  loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil.switchFrame();
		
		
	     
	}
	

	
	@Test(priority = 1)
	public void contactvisibleornot()
	{
		contactpage = 	homepage.clickContact();
	boolean flag = contactpage.verifyContactPage();
	Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException
	
	{
	Object data [] [] = 	TestUtil.getTestData(sheetname);
	return data;
	}
	
	
	@Test(dataProvider = "getCRMTestData", priority = 2, dependsOnMethods = {"contactvisibleornot"})
	public void validateCreatenewContact(String titlee, String fname, String lname, String companyn)
	{
		
		homepage.clickNewcontact();
		//contactpage.createNewcontact("Mr.", "Kushal", "Raj", "HCLTech");
		contactpage.createNewcontact(titlee, fname, lname, companyn);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
