package day21;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFilewithAutoIT {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.sodapdf.com/png-to-pdf/");
		
		WebElement uploadFile=driver.findElement(By.cssSelector("label[aria-label='Choose file']"));
		uploadFile.click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Coding stuff\\Check\\fileupload.exe");
		
		// Execute the AutoIt script using ProcessBuilder
		//ProcessBuilder pb = new ProcessBuilder("C:\\Coding stuff\\Check\\fileupload.exe");
		//pb.start();

	}

}
