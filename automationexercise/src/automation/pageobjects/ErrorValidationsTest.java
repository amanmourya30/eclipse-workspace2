package automation.pageobjects;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {
	
	@Test
	public void LoginErrorValidation() throws IOException {
		landingPage.loginApplication("azassociates3123@gmail.com", "Qwerty@123");
		Assert.assertEquals("Your email or password is incorrect!", landingPage.getErrorMsg());
	}
	

}
