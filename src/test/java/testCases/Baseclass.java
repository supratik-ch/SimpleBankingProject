package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class Baseclass {
	
	ReadConfig rconf=new ReadConfig();

	public static WebDriver driver;
	public static Logger logger;
	public String baseUrl=rconf.getApplicationURL();
	public String username=rconf.getUserName();
	public String password=rconf.getPassword();
	
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger=Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(baseUrl);
		logger.info("Url is opened");
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("Driver closed");
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		File Source = ts.getScreenshotAs(OutputType.FILE);
//		File Target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
//		FileUtils.copyFile(Source, Target);
//		System.out.println("Screenshot taken");
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");		
		FileUtils.copyFile(source, target);		
	}
}

