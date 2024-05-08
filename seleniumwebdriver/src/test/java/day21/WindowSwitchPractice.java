package day21;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowSwitchPractice {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://testautomationpractice.blogspot.com/");
		String HomeTab= driver.getWindowHandle();
		
		driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("Selenium", Keys.RETURN);
//		Thread.sleep(5000);
		try {
            Thread.sleep(3000); // Adjust wait time as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		List<WebElement> results=driver.findElements(By.xpath("//div[contains(@id,'search-results')]/descendant::div/a"));
		
		System.out.println(results.size());

		for(WebElement result:results)
		{
			result.click();;
		}
		WindowSwitchPractice.switchAndCloseWindow(driver, "Selenium (software) - Wikipedia");

		driver.switchTo().window(HomeTab);
	}
	public static void switchAndCloseWindow(WebDriver driver, String expectedTitle) {
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            String title = driver.switchTo().window(windowHandle).getTitle();
            System.out.println("Title of the current page: " + title);
            if (title.equals(expectedTitle)) {
                driver.close(); // Closing the window with the expected title
                return; // Exit the method after closing the window
            }
        }
	}
}	