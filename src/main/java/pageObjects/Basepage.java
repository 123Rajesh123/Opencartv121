package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
	
	WebDriver driver;
	
	public Basepage(WebDriver driver) {//consuctor
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
