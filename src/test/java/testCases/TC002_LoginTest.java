package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AfterLoginPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})//Master group make for execute all the test cases
	public void verify_login() {
		
		logger.info("*********Starting TC002_LoginTest*********");
		
		try {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		
		//LogIn page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//Afetr Login Page Validation
		AfterLoginPage alp=new AfterLoginPage(driver);
		boolean targerPage = alp.isAfterLogInPageExists();
		
		//Assert.assertEquals(targerPage, true, "LogIn Failed");//OR
		Assert.assertTrue(targerPage);
		
		}catch(Exception e) {
		 logger.info("******Finished TC002_LoginTest********");
		 }
	}

}
