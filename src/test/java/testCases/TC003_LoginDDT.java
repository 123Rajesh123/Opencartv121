package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AfterLoginPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven")//Getting data provider from different class
	//Then use --> dataProviderClass=DataProviders.class (DataProviders-->class name) but present in same class then not needed
	public void verify_loginDDT(String email, String pwd, String exp) {// String exp-->use for validation expected result
		
		logger.info("******** Start TC003_LoginDDT *********");
		
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//After Login Page 
		AfterLoginPage alp=new AfterLoginPage(driver);
		boolean targetpage=alp.isAfterLogInPageExists();
		
		/*
		 1. Data is valid - *login success - test pass - logout
		                    *login failed - test fail
		  
		 2. Data is invalid - *login success - test fail - logout
		                      *login failed - test pass
		 */
		if(exp.equalsIgnoreCase("Valid")) {//1.Data is Valid
			if(targetpage==true) {
				Assert.assertTrue(true);
				alp.clickLogout();
			}else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {//2.Data is Invalid
			if(targetpage==true) {
				alp.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("********* Finished TC003_LoginDDT ********");
		
	}

}
