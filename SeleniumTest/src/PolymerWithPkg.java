import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.sukgu.*;


public class PolymerWithPkg {
	
	static WebDriver driver;
	
	public static void main(String[] args) 
	 {
	
	ChromeOptions ops = new ChromeOptions();
	ops.addArguments("--disable-notifications");	 
   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
    driver = new ChromeDriver(ops);
    JavascriptExecutor js = (JavascriptExecutor)driver;
    Shadow shadow = new Shadow(driver);
    
    driver.get("https://shop.polymer-project.org/");
    driver.manage().window().maximize();
  //  js.executeScript("document.querySelector(\"body > shop-app\").shadowRoot.querySelector(\"iron-pages > shop-home\").shadowRoot.querySelector(\"div:nth-child(2) > shop-button > a\").click();");
    
    //Get first shadow host and access its shadow root
    WebElement host3 = driver.findElement(By.cssSelector("shop-home.iron-selected"));
    WebElement root3 = getShadowRoot(host3);
    
    WebElement element = shadow.getShadowElement(root3, "div:nth-child(2)>shop-button>a");
    element.click();
    
	 }
	
	public static WebElement getShadowRoot(WebElement host) {
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", host);
		  return shadowRoot;
		}

}
