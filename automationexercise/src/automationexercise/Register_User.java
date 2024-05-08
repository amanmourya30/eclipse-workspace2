package automationexercise;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Register_User {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://automationexercise.com/");
		
		//Verify that home page is visible successfully
		boolean logoIsDisplayed =driver.findElement(By.xpath("//img[@alt='Website for automation practice']")).isDisplayed();
		System.out.println("Site is loaded & logo displayed: "+logoIsDisplayed);
		
		//Click on 'Signup / Login' button
		driver.findElement(By.partialLinkText("Signup")).click();
		
		//Verify 'New User Signup!' is visible
		String NewUserTxt=driver.findElement(By.xpath("//div//h2[text()='New User Signup!']")).getText();
		if(NewUserTxt.equals("New User Signup!")) {
			System.out.println("New User Signup!" + " is visible.");
		}else {
			System.out.println("New User Signup! is not visible");
		}
		//Enter name and email address & Click 'Signup' button
		// Generate a random email address
        String randomEmail = generateRandomEmail();
        String accName="Aman mourya";
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(accName);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
		
		//Printing Email generated and password for later use
		System.out.println("Email: " + randomEmail + " Password is: Qwerty@123");
		
		//Verify that 'ENTER ACCOUNT INFORMATION' is visible
		String EnterInfoTxt=driver.findElement(By.xpath("//div//b[text()='Enter Account Information']")).getText();
		System.out.println("Enter Account Information is Displayed: "+EnterInfoTxt.equals("ENTER ACCOUNT INFORMATION"));
		
		//radio button
		driver.findElement(By.id("id_gender1")).click();
		
		//handling dropdown
        // Locate the dropdown element
        WebElement dropdownDays = driver.findElement(By.id("days"));
        // Create a Select object
        Select dropdown = new Select(dropdownDays);
        // Select an option by visible text
        dropdown.selectByVisibleText("25");
        
        Select dropdown2 = new Select(driver.findElement(By.id("months")));
        dropdown2.selectByVisibleText("May");
		
        Select dropdown3 = new Select(driver.findElement(By.id("years")));
        dropdown3.selectByValue("2003");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
		 //blocking ads

			js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
			js.executeScript("window.scrollBy(0,350)");
			try {
	            WebElement dismissButton = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
	            dismissButton.click();
	            System.out.println("Dismissed the ad.");
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            System.out.println("No ad found. Proceeding with the script.");
	        }
        
        driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		
		// Define the details to fill
        Map<String, String> details = new HashMap<>();
        details.put("//input[@name='password']", "Qwerty@123");
        details.put("//input[@name='first_name']", "Aman");
        details.put("//input[@name='last_name']", "mourya");
        details.put("//input[@name='company']", "Xten-AV");
        details.put("//input[@name='address1']", "Shradha colony");
        details.put("//input[@name='address2']", "Apt 101");
        details.put("//input[@name='state']", "Uttar pradesh");
        details.put("//input[@name='city']", "Noida");
        details.put("//input[@name='zipcode']", "201307");
        details.put("//input[@name='mobile_number']", "1234567890");

        // Fill the details
        fillDetails(driver, details);
        
        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
        
        //Verify that 'ACCOUNT CREATED!' is visible & click continue
        String accountCreated= driver.findElement(By.xpath("//b[text()='Account Created!']")).getText();
        System.out.println("Account created: "+accountCreated.equals("ACCOUNT CREATED!"));
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		
		// Handle ad if it appears
        try {
            WebElement dismissButton = driver.findElement(By.xpath("//*[@id='dismiss-button']"));
            dismissButton.click();
            System.out.println("Dismissed the ad.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("No ad found. Proceeding with the script.");
        }
        
        //Verify that 'Logged in as username' is visible
		String LoggedInAs=driver.findElement(By.xpath("//li[10]//a[1]")).getText();
		System.out.println(LoggedInAs);
		if (LoggedInAs.equals(" Logged in as "+accName)) {
			System.out.println(" Logged in as "+ accName);			
		}
		//Click 'Delete Account' button & Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();
		String accDeletedTxt=driver.findElement(By.xpath("//h2[@data-qa='account-deleted']")).getText();
		if(accDeletedTxt.equals("ACCOUNT DELETED!")) {
			System.out.println("ACCOUNT DELETED! Text is showing");
		}
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
	}

	// Method to generate a random email address
    public static String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) { // Generate a random local part of length 10
            email.append(allowedChars.charAt(rnd.nextInt(allowedChars.length())));
        }
        email.append("@example.com"); // Append a fixed domain
        return email.toString();
    }
    
    public static void fillDetails(WebDriver driver, Map<String, String> details) {
        for (Map.Entry<String, String> entry : details.entrySet()) {
            WebElement element = driver.findElement(By.xpath(entry.getKey()));
            element.sendKeys(entry.getValue());
        }
    }
}
