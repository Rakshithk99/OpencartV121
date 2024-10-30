package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is Valid, Login Success - Test Passed - Log out
 * Data is Valid, Login Failure - Test Failed
 * Data is Invalid, Login Success - Test Failed - Log out
 * Data is Invalid, Login Failure - Test Passed 
 * 
 * 
 */


public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) {
		logger.info("*****Starting TC003_LoginTestDDT*****");
		try {
		//Home Page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountDisplayed();
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				Assert.assertTrue(true);
				macc.clickLogout();
			} else {
				Assert.assertTrue(false);
			}
		} else {
			if(targetPage==true){
				macc.clickLogout();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		Log.info("*****Finished TC003_LoginTestDDT*****");
		
		
	}
	
}
