package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUsrName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath = "//*[@href='Logout.php']")
	WebElement btnLogout;
	
	public void setUserName(String uname) {
		txtUsrName.sendKeys(uname);
	}
	
	public void setPassword(String psswrd) {
		txtPassword.sendKeys(psswrd);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}
	
	public void clickLogout() {
		btnLogout.click();
	}
}
