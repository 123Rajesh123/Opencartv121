package testCases;

/*Organise imports click then all unneccessary import remove
Simple cursor go import in yellow line if unneccessary import is there yellow line is show, Organise imports option is show */

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})//@Test(group="Regression")-->single group if more than one group use curlry bracket given
	public void verify_account_registration() {
		
		logger.info("******** TC001_AccountRegistrationTest **********");
		try {
			
		HomePage hp=new HomePage(driver);
		hp.clickResgister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage  arp=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		arp.setMalebtn();
		arp.setTextFirstname(randomString().toUpperCase());//First Name is lower or upper both case convert in upper case
		arp.setTextLastName(randomString().toUpperCase());//Last Name is lower or upper both case convert in upper case
		arp.setTextEmail(randomString()+"@gmail.com");//Randomly generated the email
		
		//When I use randomAlphaNumeric() every time change value so my Password And Confirm Password both are not same
		//so my test case is fail, so first store Password in variable and this variable pass in Password And Confirm Password
		
		String password = randomAlphaNumeric();
		
		arp.setTextPassword(password);
		arp.setTextConfirmPassword(password);
		arp.setRegistrationBtn();
		
		//Validation
		logger.info("Validating expected message");
		String confmsg = arp.getConfirmationMsg();
		if(confmsg.equals("Your registration completed")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test failed....");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(confmsg, "Your registration completed");
		//click Continue Button
		arp.setContinueBtn();
		
	 }catch(Exception e) {
		 
		Assert.fail();
	  }
		
		logger.info("******* Finished TC001_AccountRegistrationTest **********");
		
  }
		

}
