package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends Basepage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
//	@FindBy(xpath = "//label[text()='Male']")
//  or other way-->hoe->variable, How->Predefined class 'H' in capital letter, using->Keyword
	@FindBy(how=How.XPATH, using = "//label[text()='Male']")
	WebElement Malebtn;
	
	@FindBy(id = "FirstName")
	WebElement textFirstname;
	
	@FindBy(id = "LastName")
	WebElement textLastName;
	
	@FindBy(id = "Email")
	WebElement textEmail;
	
	@FindBy(id = "Password")
	WebElement textPassword;
	
	@FindBy(id = "ConfirmPassword")
	WebElement textConfirmPassword;
	
	@FindBy(id = "register-button")
	WebElement RegistrationBtn;
	
	@FindBy(xpath = "//div[contains(text(),'Your registration completed')]")
	WebElement msgConfirmation;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;

	public void setMalebtn() {
		Malebtn.click();
	}

	public void setTextFirstname(String FN) {
		textFirstname.sendKeys(FN);
	}

	public void setTextLastName(String LN) {
		textLastName.sendKeys(LN);
	}

	public void setTextEmail(String email) {
		textEmail.sendKeys(email);
	}

	public void setTextPassword(String pwd) {
		textPassword.sendKeys(pwd);
	}

	public void setTextConfirmPassword(String cpwd) {
		textConfirmPassword.sendKeys(cpwd);
	}

	public void setRegistrationBtn() {
		//sol 1
		RegistrationBtn.click();
		//sol 2
//		RegistrationBtn.submit();
		//sol 3
//		Actions act=new Actions(driver);
//		act.moveToElement(RegistrationBtn).click();
		//sol 4
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", RegistrationBtn);
		//sol 5
//		RegistrationBtn.sendKeys(Keys.RETURN);
		//sol 6
//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(RegistrationBtn)).click();
		
	}
	
	public void setContinueBtn() {
		continueBtn.click();
	}
	
	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	

}

