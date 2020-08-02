import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.sukgu.*;

public class UsingPkg {
	
	static WebDriver driver;
	
	public static void main(String[] args) 
	 {
	
	ChromeOptions ops = new ChromeOptions();
	ops.addArguments("--disable-notifications");	 
   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
    driver = new ChromeDriver(ops);
    Shadow shadow = new Shadow(driver);
    
   WebDriverWait wait = new WebDriverWait(driver,10);
   
   driver.get("chrome://downloads/");
   driver.manage().window().maximize();
   WebElement element = shadow.findElement("div[id='leftSpacer']>h1");
   String text = element.getText();
   System.out.println("*****"+text+"****");
   String searchText= shadow.findElement("div[id='searchTerm']>label#prompt").getText();
   System.out.println("*****"+searchText+"****");
   WebElement searchInput=shadow.findElement("div[id='searchTerm']>input#searchInput");
   searchInput.sendKeys("It Worked");
	
}
	
}
