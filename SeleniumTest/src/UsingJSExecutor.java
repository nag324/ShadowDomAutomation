import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsingJSExecutor {
	
	
	static WebDriver driver;
	
	public static void main(String[] args) 
	 {
		
		

	ChromeOptions ops = new ChromeOptions();
	ops.addArguments("--disable-notifications");	 
   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
    driver = new ChromeDriver(ops);
   WebDriverWait wait = new WebDriverWait(driver,10);
   
   driver.get("chrome://downloads/");
   driver.manage().window().maximize();
   
   WebElement root1 = driver.findElement(By.tagName("downloads-manager"));
   WebElement firstRoot = getShadowRootElement(root1);
   
   WebElement root2 = firstRoot.findElement(By.cssSelector("downloads-toolbar"));
	WebElement secondRoot = getShadowRootElement(root2);

	WebElement root3 = secondRoot.findElement(By.cssSelector("cr-toolbar"));
	WebElement thirdRoot = getShadowRootElement(root3);
	
	

	String actualHeading = thirdRoot.findElement(By.cssSelector("div[id='leftSpacer']>h1")).getText();

	System.out.println("*****"+actualHeading+"****");
	
	WebElement root4 = thirdRoot.findElement(By.cssSelector("cr-toolbar-search-field"));
	WebElement finalRoot = getShadowRootElement(root4);
	String searchText= finalRoot.findElement(By.cssSelector("div[id='searchTerm']>label#prompt")).getText();
	System.out.println("*****"+searchText+"****");
	
	finalRoot.findElement(By.cssSelector("div[id='searchTerm']>input#searchInput")).sendKeys("Test");
    
	
	
	 }

	
	public static WebElement getShadowRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
										.executeScript("return arguments[0].shadowRoot",element);
		return ele;
	}

}
