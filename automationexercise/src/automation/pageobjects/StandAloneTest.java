package automation.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StandAloneTest extends BaseTest{

	@Test(dataProvider="getData",groups={"purchase"})
	public void AloneTest(String email, String password, String productName) throws IOException {
		
		landingPage.loginApplication(email,password);
		ProductCatalogue productCatalogue = landingPage.goToProductPage();

		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.addedConfirmation("Your product has been added to cart.");	
		
		cartPage.cartConfirmation(productName);
		PaymentPage paymentPage = cartPage.checkoutAndPlaceOrder();
		
		paymentPage.fillPaymentDetail("Aman kumar", "123456789", "123", "02", "2036");
		paymentPage.orderConfirmation();
	}
	
	@DataProvider
	public Object[][] getData() 
	{
		return new Object [][] {{"azassociates31@gmail.com","Qwerty@123","Madame Top For Women"},
								{"dw1yuiatn6@example.com","Qwerty@123","Blue Cotton Indie Mickey Dress"}};
	}

}
