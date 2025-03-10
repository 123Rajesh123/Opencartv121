package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Basepage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
    @FindBy(id = "Email")
    WebElement txtEmailAddress;
    
    @FindBy(id = "Password")
    WebElement txtPassword;
    
    @FindBy(xpath = "//input[@value='Log in']")
    WebElement btnLogIn;
    
    public void setEmail(String email) {
    	txtEmailAddress.sendKeys(email);
    }
    
    public void setPassword(String pwd) {
    	txtPassword.sendKeys(pwd);
    }
    
    public void clickLogin() {
    	btnLogIn.click();
    }
}
