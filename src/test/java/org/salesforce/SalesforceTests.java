package org.salesforce;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;


public  class SalesforceTests {
    static private String  appName  = "Salesforce";

    // change the value of testName so that it has a unique value on your Eyes system
  //  static private String  testName = "Salesforce Tests";

    // if you have a dedicated Eyes server, set the value of the variable serverURLstr to your URL
    static String serverURLstr = "https://eyesapi.applitools.com";

    //set the value of runAsBatch to true so that the tests run as a single batch
    static private Boolean runAsBatch = true;

    // set the value of changeTest to true to introduce changes that Eyes will detect as mismatches
    static private Boolean changeTest = false;

    static private String  weburl = "https://nagsftest-dev-ed.lightning.force.com/";

    public static void main(String[] args) {
        URI serverURL;
        try {
            serverURL = new URI(serverURLstr);
        } catch (URISyntaxException e) {
            System.out.println("URI Exception ");
            return;
        }
        Eyes eyes = new Eyes();
        eyes.setServerUrl(serverURL);
        setup(eyes);
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        String path = "C://chrome//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver innerDriver = new ChromeDriver(ops);  // Open a Chrome browser.
        innerDriver.manage().window().maximize();
        innerDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // RectangleSize viewportSizeLandscape = new RectangleSize(/*width*/ 1024, /*height*/ 768 );
      //  RectangleSize viewportSizePortrait = new RectangleSize(/*width*/ 500, /*height*/ 900 );
       // SalesforceLogin(innerDriver,eyes);
        GoToApplication(innerDriver,eyes);

        innerDriver.quit();
       // innerDriver.close();

   }

    static private void SalesforceLogin(WebDriver innerDriver, Eyes eyes) {
        // Start the test and set the browser's viewport size
        WebDriver driver = eyes.open(innerDriver, appName, "SalesforceLogin");


        try {
            driver.get(weburl);

          //  eyes.checkWindow("Salesforce Login");
           // WebDriverWait wait = new WebDriverWait(driver,10);
          //  wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username"))).sendKeys("nag@nznetwork.com");;
            driver.findElement(By.name("username")).sendKeys("nag@nznetwork.com");
           // wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("Salesforce324");;
            driver.findElement(By.id("password")).sendKeys("Salesforce324");
            driver.findElement(By.name("Login")).click();
          //  wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Login"))).click();
          //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           // eyes.checkWindow("Home Screen");



           // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[2]/div[1]/div/div/div/div/div/input")));

            // Visual checkpoint 1
            eyes.checkWindow("Home Screen");

            TestResults result = eyes.close(false); //false means don't thow exception for failed tests
            handleResult(result);
        } finally {
            eyes.abortIfNotClosed();
        }
    }


    static private void GoToApplication(WebDriver innerDriver, Eyes eyes) {
        // Start the test and set the browser's viewport size
        WebDriver driver = eyes.open(innerDriver, appName, "GoToApplication");


        try {
            driver.findElement(By.className("slds-icon-waffle")).click();
            driver.findElement(By.xpath("//label[text()='Search apps and items...']/following::input")).sendKeys("Test App");
            driver.findElement(By.xpath("(//lightning-formatted-rich-text[contains(@class,'al-menu-item-label slds-truncate')]//p)[1]")).click();
           // WebDriverWait wait = new WebDriverWait(driver,10);
           // wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Test Customer")));
            WebElement element = driver.findElement(By.xpath("(//span[text()='Test Customer'])[1]"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);

            eyes.checkWindow("App Summary");

            TestResults result = eyes.close(false); //false means don't thow exception for failed tests
            handleResult(result);
        } finally {
            eyes.abortIfNotClosed();
        }
    }

    static private  void handleResult(TestResults result) {
        String resultStr;
        String url;
        if (result == null) {
            resultStr = "Test aborted";
            url = "undefined";
        } else {
            url = result.getUrl();
            int totalSteps = result.getSteps();
            if (result.isNew()) {
                resultStr = "New Baseline Created: " + totalSteps + " steps";
            } else if (result.isPassed()) {
                resultStr = "All steps passed:     " + totalSteps + " steps";
            } else {
                resultStr = "Test Failed     :     " + totalSteps + " steps";
                resultStr += " matches=" +  result.getMatches();      /*  matched the baseline */
                resultStr += " missing=" + result.getMissing();       /* missing in the test*/
                resultStr += " mismatches=" + result.getMismatches(); /* did not match the baseline */
            }
        }
        resultStr += "\n" + "results at " + url;
        System.out.println(resultStr);
    }


    static private void setup(Eyes eyes) {
        String apiKey = "KFT6wNjOm4BGeK4JZS9OVAYwOF4nc1119I99pYW5MmP4YM110";
        eyes.setApiKey(apiKey);
        if (runAsBatch) {
            BatchInfo batchInfo = new BatchInfo("Salesforce");
            eyes.setBatch(batchInfo);
        }

        //eliminate artifacts caused by a blinking cursor - on by default in latest SDK
        eyes.setIgnoreCaret(true);
    }


}

