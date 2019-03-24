package tools;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import tools.customDriver.AttachedWebDriver2;

/**
 *  This class is for testing purpose
 * @author Arpo Adhikari
 *
 */
public class SessionTest {
	
	public static WebDriver driver;

	public static void main(String[] args) throws MalformedURLException, AWTException, InterruptedException {
		
		String sessionId = "468c9292aec2bde0ea039b6a819c75b8";
		URL url = new URL("http://localhost:42830");
		driver = new AttachedWebDriver2(url, sessionId);
		//((RemoteWebDriver)driver).setLogLevel(Level.INFO);
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().defaultContent();
		List<WebElement> elements = driver.findElements(By.xpath("//iframe | //frame"));
		for (WebElement webElement : elements) {
			//System.out.println(webElement.getAttribute("outerHTML"));
			System.out.println(webElement.getTagName());
			System.out.println((String)((JavascriptExecutor)driver).executeScript("return arguments[0].outerHTML;", webElement));
		}
		
		System.out.println("===========");
		String xpath  = "//h5[text()='Unique & Clean']";
		List<WebElement> elementList = driver.findElements(By.xpath(xpath));
		if (elementList.size() ==  0) {
			List<WebElement> iframes = driver.findElements(By.xpath("//iframe | //frame"));
			for (WebElement iframe : iframes) {
				driver.switchTo().frame(iframe);
				List<WebElement> elementList1 = driver.findElements(By.xpath(xpath));
				if (elementList1.size() > 0) {
					System.out.println("Found in iframe");
					break;
				}
				driver.switchTo().defaultContent();
			}
		}
		else {
			System.out.println("Found outside iframe");
		}
	}

}
