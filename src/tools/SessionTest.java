package tools;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import tools.customDriver.AttachedWebDriver2;

/**
 *  This class is for testing purpose
 * @author Arpo Adhikari
 *
 */
public class SessionTest {

	public static void main(String[] args) throws MalformedURLException, AWTException, InterruptedException {
		
		String sessionId = "0498fc28-5aed-4612-a161-21bfdb8f0e77";
		URL url = new URL("http://localhost:39235");
		WebDriver driver = new AttachedWebDriver2(url, sessionId);
		System.out.println(driver.getCurrentUrl());
		driver.get("https://www.google.com");
		Thread.sleep(5000);
		driver.findElement(By.name("q")).sendKeys("Selenium"+Keys.ENTER);

	}

}
