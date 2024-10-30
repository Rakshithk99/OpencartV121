package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	public WebDriver driver;
	
	
	@Test(groups={"regression","Master"})
	public void verify_account_registration() {
		logger.info("**** Starting TC001_AccountRegistrationTest **** ");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer Information");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@Gmail.com");
		regpage.setPassword("Rak12345");
		regpage.acceptTerms();
		regpage.continueBtn();
		
		logger.info("Validating expected message");
		String confirmationMsg = regpage.getConfirmationMsg();
		
		
		Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed...");
			logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("**** Finished TC001_AccountRegistrationTest **** ");
	}
	
	
}
