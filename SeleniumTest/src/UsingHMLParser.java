import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UsingHMLParser {
	
	static WebDriver driver;
	
	public static void main(String[] args) throws IOException 
	 {
		

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");	 
	   System.setProperty("webdriver.chrome.driver", "C:\\chrome\\latest\\chromedriver.exe");
	    driver = new ChromeDriver(ops);
	   WebDriverWait wait = new WebDriverWait(driver,10);	   
	   driver.get("chrome://downloads/");
	   driver.manage().window().maximize();
	   Document doc = Jsoup.connect("http://example.com/").get();
	   String text = doc.select("div[id='leftSpacer']>h1").text();
	   System.out.println("*****"+text+"@@@@@@@");
	   
	  
		
		
		
		
	 }

}
