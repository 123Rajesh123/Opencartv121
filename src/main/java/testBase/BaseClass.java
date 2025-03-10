package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j

public class BaseClass {
	
public static WebDriver driver;
public Logger logger; //Log4j--->
public Properties p; 
	
	@BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Throwable {
//	public void setup() throws Throwable {	
		//Loading config.properties file
		FileReader file=new FileReader(".\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		//Remote execution in Grid
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os-->operating System
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("linux")){
				capabilities.setPlatform(Platform.LINUX);
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("No matching os");
				return; //No matching os then Exit from if codition
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default: System.out.println("No matching browser");return;//Exit from switch
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities );
			
		}
		
		//Local execution on Grid
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			 
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox" : driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;//return use it means browser is invalid then next
	        //step test case is not executed 
			}
		}
		
		
		
	
	/*	switch(br.toLowerCase()) {
		 
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.."); return;//return use it means browser is invalid then next
        //step test case is not executed 
		}      */
		
//		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(p.getProperty("appURL2"));//Reading url from properties file
		
	}
	
	@AfterClass(groups={"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		
		driver.quit();
	}
	
	
	//Using the dependency common lang3  this method(RandomStringUtils) in not directly availble in java
		public String randomString() {
			 String generatedstring = RandomStringUtils.randomAlphabetic(5);//5->generate five character mail id
			 //Every time five character different id generate
			 return generatedstring;
		}
		
		public String randomNumber() {
			//Generate 10 digit random number but mobile number in String format 
			String generatednumber = RandomStringUtils.randomNumeric(10);
			return  generatednumber;
		}
		
		public String randomAlphaNumeric() {
			//Generate combination of 3 digit of Alphabets and 3 digit of Numbers total 6 digit
			 String generatedstring = RandomStringUtils.randomAlphabetic(3);
			 String generatednumber = RandomStringUtils.randomNumeric(3);
			 return (generatedstring+"@"+generatednumber);//Add @ special characters using in my Password
		}
		
		public String captureScreen(String tname) throws IOException{
			
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
			
			TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
			File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+".\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath; //Attach to the ScreenShot in Report it's return targetFilePath is mandatory
		}
}
