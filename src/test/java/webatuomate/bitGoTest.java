package webatuomate;

//deiva-s
//harman-bitgo
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class bitGoTest {
    WebDriver driver;
    @BeforeClass
    public void setup() {
    	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
	      
    }
    
	@Test(priority=1)
	public void program1() {
		
	      WebElement  ele1 = driver.findElement(By.className("transactions"));
	      
	      JavascriptExecutor js = (JavascriptExecutor) driver;
	      js.executeScript("arguments[0].scrollIntoView();", ele1);
	      String title = driver.findElement(By.className("font-h3")).getText();
	      Assert.assertEquals(title, "25 of 2875 Transactions");
	      
	}
	
	@Test(priority=2)
	public void program2() {
		
	
	      List<WebElement> transactions = driver.findElements(By.cssSelector(".transaction-box"));
	        int count = 0;

	        for (WebElement transaction : transactions) {
	            if (count >= 25) break; // Limit to 25 transactions

	            // Get Transaction Hash
	            WebElement txnElement = transaction.findElement(By.cssSelector(".txn a"));
	            String transactionHash = txnElement.getText();

	            // Count VINs (inputs)
	            List<WebElement> vins = transaction.findElements(By.cssSelector(".vins .vin"));
	            int vinCount = vins.size();

	            // Count VOUTs (outputs)
	            List<WebElement> vouts = transaction.findElements(By.cssSelector(".vouts .vout"));
	            int voutCount = vouts.size();

	            // Check if the transaction has exactly 1 VIN and 2 VOUTs
	            if (vinCount == 1 && voutCount == 2) {
	                System.out.println("Transaction Hash: " + transactionHash);
	            }

	            count++;
	        }
	        System.out.println(count);

	}
	
	 @AfterClass
	    public void tearDown() {
	        driver.quit(); 
	    }

}
