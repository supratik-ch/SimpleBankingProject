package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_Login_001 extends Baseclass {

	@Test
	public void loginTest() {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		if(driver.getTitle().equals("GTPL Bank Manager HomePag")) {
			Assert.assertTrue(true);
			logger.info("Test Passed");
		}
		else {
			try {
				captureScreen(driver, "loginTest");  		//name of image file must be same as function name...or else
															//during test fail...extent report will not find the image screenshot
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(false);
			logger.info("Test Failed");
		}
	}
}
