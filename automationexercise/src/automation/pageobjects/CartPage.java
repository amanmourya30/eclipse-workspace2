package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest{

		WebDriver driver;
		public CartPage(WebDriver driver) {
			//initialization
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath= "//td[@class='cart_description']//a")
		WebElement productInCart;
		@FindBy(xpath= "//a[contains(text(),'Proceed To Checkout')]")
		WebElement checkout;
		@FindBy(xpath= "//a[contains(text(),'Place Order')]")
		WebElement placeOrder;
				
		public void cartConfirmation(String productName) {
			String cartProductTxt= productInCart.getText();
			if(cartProductTxt.equalsIgnoreCase(productName)) {
				System.out.println("Same product Added in Cart");
			}		
		}
		
		public PaymentPage checkoutAndPlaceOrder(){
			checkout.click();
			placeOrder.click();
			PaymentPage paymentPage=new PaymentPage(driver);
			return paymentPage;
		}
		
		
	
	}

