package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;    //Report Name
	
	public void onStart(ITestContext testContext) {//testContext-->which test case executed
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.HH.mm.ss"); 
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		 */
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//Specify the location of the report
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title the report
		sparkReporter.config().setReportName("opencart Functional Testing");//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);//os-->passing the parameter of suitexml file and also use in Baseclass
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);//browser-->passing the parameter of suitexml file and also use in Baseclass
		//browser and os name find through from the suitexml file it's dynamically when you change in xml file
		
		List<String> includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
		 extent.setSystemInfo("Groups", includeGroups.toString());//If you run group xml file then show the name which group
		 //you run they are present in <include name=""/> and group in not given or empty the not display anything
		}
		
	}
	
	    public void onTestSuccess(ITestResult result) {
	    	
	    	test=extent.createTest(result.getTestClass().getName());// To display class name
	    	test.assignCategory(result.getMethod().getGroups());// To display groups in report
	    	test.log(Status.PASS, result.getName()+" got successfully executed");
	    }
	    
	    public void onTestFailure(ITestResult result) {
	    	
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	
	    	test.log(Status.FAIL, result.getName()+" got failed");
	    	test.log(Status.INFO, result.getThrowable().getMessage());
	    	
	    	try {
	    		String imgPath = new BaseClass().captureScreen(result.getName());//In Baseclass ScreenShot method is non-static
	    		//Not direct access first create object of BaseClass then give Screenshots method and then give parameter name
	    		//In ExtentReport Base class object inside one more driver is different from Base class driver there are two driver
	    		//show conflicts, we need to make "webdriver as Satic" in Base class then same driver will be refer in the object also.
	    		//In java multiple objects to make a common variables only by making the variable is static
	    		test.addScreenCaptureFromPath(imgPath);
	    		
	    	}catch(IOException e1) {
	    		e1.printStackTrace();//This method use display the exception msg 
	    	}
	    }
	    
	    public void onTestSkipped(ITestResult result) {
	    	
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.log(Status.SKIP, result.getName() + "got skipped");
	    	test.log(Status.INFO, result.getThrowable().getMessage());//Give the msg why get skipped
	    }
	    
	    public void onFinish(ITestContext testContext) {
	    //I want open a report automatically not open manually that's use below all code in this onFinish()	
	    	extent.flush();
	    	
	    	String pathofExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
	    	File extentReport=new File(pathofExtentReport);
	    	
	    	try {
	    		
	    		Desktop.getDesktop().browse(extentReport.toURI());//Open the report on the Browser automatically
	    		
	    	}catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	
	   /* 	 try {
	    			 
	    	   URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	    	 
	    	   //Create the email message and send different person
	    	   ImageHtmlEmail email=new ImageHtmlEmail();//Add the dependency search java email--> add Apache Commons Email
	    	   
	    	   email.setDataSourceResolver(new DataSourceUrlResolver(url));
	    	   email.setHostName("smtp.google.com");
	    	   email.setSmtpPort(465);
	    	   email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
	    	   email.setSSLOnConnect(true);
	    	   email.setFrom("pavanoltraining@gmail.com");//Sender
	    	   email.setSubject("Test Results");
	    	   email.setMsg("Please find Attached Report......");
	    	   email.addTo("pavankumar.busyqa@gmail.com");//Receiver
	    	   email.attach(url, "Extent Report", "Please check report");
	    	   email.send();//Send the mail
	    	   
	    	 }catch(Exception e) {
	    		 e.printStackTrace();
	    	 }
	   */ 
	    	
	    }

}
 