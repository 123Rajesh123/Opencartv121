package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Basepage{
	
	public HomePage(WebDriver driver) { //constructor
		
		super(driver);
	}
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//a[text()='Log in']")
	WebElement lnkLogIn;
	
	public void clickResgister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogIn.click();
	}

}
