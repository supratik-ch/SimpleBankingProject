package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.NewExcelLibrary;

public class TC_DDLogin_002 extends Baseclass{

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pswd) {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pswd);
		lp.clickSubmit();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (isAlertPresent()==true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.info("Invalid Username/Password");
		}
		else {
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Login Successfull");
		}
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() {
		String path = System.getProperty("user.dir")+"/src/test/java/testData/Test Data.xlsx";
		
		NewExcelLibrary excelObj = new NewExcelLibrary(path);
		int rownum=excelObj.getRowCount("Sheet1");
		int colcount=excelObj.getColumnCount("Sheet1");
		String loginData[][]=new String[rownum-1][colcount];
		
		for(int i=1;i<rownum;i++) {
			for(int j=0;j<colcount;j++) {
				loginData[i-1][j]=excelObj.getCellData("Sheet1", j, i+1);
			}
		}
		return loginData;	
	}
}
