
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PolymerTest {
	
	static WebDriver driver;
	
	public static void main(String[] args) 
	 {
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");	 
	    System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
	    driver = new ChromeDriver(ops);	    
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    WebDriverWait wait = new WebDriverWait(driver,10);
	    
	    driver.get("https://shop.polymer-project.org/");
	    driver.manage().window().maximize();
	   // WebElement root1 = driver.findElement(By.tagName("shop-app"));
	   
	   //WebElement btn = (WebElement) js.executeScript("return document.querySelector('body>shop-app').shadowRoot.querySelector('iron-pages').shadowRoot.querySelector('shop-home').shadowRoot.querySelector('div:nth-child(2)>shop-button>a')");
	    
	  // btn.click();
	    
	  //Get first shadow host and access its shadow root
	    WebElement host1 = driver.findElement(By.tagName("shop-app"));
	    WebElement root1 = getShadowRoot(host1);
	    
	  //Get second shadow host and access its shadow root
	    WebElement host2 = root1.findElement(By.cssSelector("iron-pages"));
	    WebElement root2 = getShadowRoot(host2);
	    
	  //Get third shadow host and access its shadow root
	    WebElement host3 = root1.findElement(By.cssSelector("shop-home.iron-selected"));
	    WebElement root3 = getShadowRoot(host3);
	    
	    WebElement btinc = root3.findElement(By.cssSelector("div:nth-child(2)>shop-button>a"));
	    btinc.click();
	    
	 }
	
	public static WebElement getShadowRoot(WebElement host) {
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", host);
		  return shadowRoot;
		}

}
