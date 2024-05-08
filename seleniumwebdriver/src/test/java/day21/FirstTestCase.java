package day21;

import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTestCase {

	public static void main(String[] args) {
		// 1) launch borwser chrome
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// 2) open URL https://demo.opencart.com/
		driver.get("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.19.1");
		String act_title = driver.getTitle();
		if (act_title.equals("Maven Repository: org.seleniumhq.selenium » selenium-java » 4.19.1")) 
		{
			System.out.println("Test is passed...");
		} else 
		{
			System.out.println("Test is Failed...");
		}
//		driver.close();
		driver.quit();
	}

}
