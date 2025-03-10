package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AfterLoginPage extends Basepage{
	
    public AfterLoginPage(WebDriver driver) {
    	super(driver);
    }
    
    @FindBy(xpath = "//div[@class='header-links-wrapper']/descendant::a[@class='account']")//After login validation through mail
    WebElement txtEmailMsg;
    
    @FindBy(xpath = "//a[text()='Log out']")  //added in step6-->Data driven Login Test
    WebElement lnkLogout;
    
//    public void findMailText() {
//    	String mailText = txtEmailMsg.getText();
//    }
    
    public boolean isAfterLogInPageExists() {
    	try {
    		return(txtEmailMsg.isDisplayed());
    	}catch(Exception e) {
    		return false;
    	}
    }
    
    public void clickLogout() {
    	lnkLogout.click();
    }
    
    
}
