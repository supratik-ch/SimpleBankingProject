package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.Baseclass;

public class Reporting extends Baseclass implements ITestListener{

	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Admin");
		
		htmlReporter.config().setDocumentTitle("Banking Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
	
	}
	public void onTestStart(ITestResult result) {
		logger = extent.createTest(result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				logger.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				logger.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				
				String imgPath = System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png";
				
				File f = new File(imgPath);
				String absPath=f.getAbsolutePath();
				if(f.exists())
				try {
					logger.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(absPath).build());
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}
	
	public void onFinish(ITestContext result) {
		extent.flush();
	}
}
