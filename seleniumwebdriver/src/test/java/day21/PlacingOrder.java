package day21;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlacingOrder {

	public static void main(String[] args) {
		ChromeOptions opt =new ChromeOptions();
		opt.addExtensions(new File("./Extensions/AdBlock.crx"));
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(opt);
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://automationexercise.com/");
		String productName="Madame Top For Women";

		//go to login page
		driver.findElement(By.cssSelector("a[href='/login']")).click();
		
		//login in account
		driver.findElement(By.name("email")).sendKeys("azassociates31@gmail.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Qwerty@123");
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		
		//go to products page
		driver.findElement(By.cssSelector("a[href='/products']")).click();
		
		//list & filter desired product 
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='features_items']//div[@class='col-sm-4']"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("p")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.xpath(".//a[contains(text(),'Add to cart')]")).click();
		
		//confirming 
		String addedToCart= driver.findElement(By.xpath("//div[@class='modal-content']//p[1]")).getText();
		String ExpectedText="Your product has been added to cart.";
		assert ExpectedText.equals(addedToCart) : "Text does not match";	
		
		//click on view cart
		driver.findElement(By.xpath("//u[normalize-space()='View Cart']")).click();
		
		//Check if product name is same as we added
		String productInCart= driver.findElement(By.cssSelector("a[href='/product_details/7']")).getText();
		if(productInCart.equalsIgnoreCase(productName)) {
			System.out.println("Same product Added in Cart");
		}
		
		//click on proceed to Checkout & Place order
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		
		//payment page
		driver.findElement(By.name("name_on_card")).sendKeys("aman kumar");
		driver.findElement(By.name("card_number")).sendKeys("123456789");
		driver.findElement(By.name("cvc")).sendKeys("123");
		driver.findElement(By.name("expiry_month")).sendKeys("02");
		driver.findElement(By.name("expiry_year")).sendKeys("2036");
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		
		//order placed text
		String orderPlacedTxt= driver.findElement(By.xpath("//h2[@data-qa='order-placed']/b")).getText();
		if(orderPlacedTxt.equalsIgnoreCase("order placed!")) {
			System.out.println("Congrats!! Order Placed Successfully!");
		}
		
		
		
		
	}

}
