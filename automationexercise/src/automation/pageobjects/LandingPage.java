package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BaseTest {

		WebDriver driver;
		public LandingPage(WebDriver driver) {
			//initialization
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		@FindBy(name= "email")
		WebElement userEmail;
		@FindBy(xpath= "//input[@data-qa='login-password']")
		WebElement passwordEle;
		@FindBy(xpath="//button[@data-qa='login-button']")
		WebElement submit;
		@FindBy(css="a[href='/login']")
		WebElement LoginPage;
		@FindBy(css="a[href='/products']")
		WebElement ProductsBtn;
		@FindBy(xpath="//input[@name='password']/following-sibling::p")
		WebElement errorMessage;
		
		public void loginApplication(String email, String password){
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			submit.click();
			
		}
		public void goTo() {
			driver.get("https://automationexercise.com/");
			LoginPage.click();
			}
		public String getErrorMsg() {
			return errorMessage.getText();
		}
		
		public ProductCatalogue goToProductPage() {
			ProductsBtn.click();
			ProductCatalogue productCatalogue=new ProductCatalogue(driver);
			return productCatalogue;
		}
	
	}

