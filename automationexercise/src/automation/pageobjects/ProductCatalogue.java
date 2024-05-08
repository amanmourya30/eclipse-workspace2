package automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends BaseTest {

		WebDriver driver;
		public ProductCatalogue(WebDriver driver) {
			//initialization
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		@FindBy(xpath= "//div[@class='features_items']//div[@class='col-sm-4']")
		List<WebElement> Products;
		@FindBy(xpath= "//div[@class='modal-content']//p[1]")
		WebElement productAdded;
		@FindBy(xpath= "//u[normalize-space()='View Cart']")
		WebElement viewCart;
		
		By addToCart=By.xpath(".//a[contains(text(),'Add to cart')]");
		
		public List<WebElement> getProductList() {
			return Products;
		}
	
		public WebElement getProductByName(String productName) {
			
			WebElement prod=getProductList().stream().filter(product->
			product.findElement(By.cssSelector("p")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductToCart(String productName) {
			WebElement prod=getProductByName(productName);
			prod.findElement(addToCart).click();	
		}
		
		public CartPage addedConfirmation(String Msg) {
			String addedToCart= productAdded.getText();
			if(addedToCart.equals(Msg)){
				System.out.println("Product Successfully added!!");
			}
			
			viewCart.click();
			CartPage cartPage=new CartPage(driver);
			return cartPage;
		
			
			
		}
	
	}

