package ifbp.testes.myanimelist.selenium;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class ExclusaoAnimeTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void exclusaoAnime() {
    driver.get("http://localhost:8080/entrar");
    driver.manage().window().setSize(new Dimension(1050, 708));
    
    try {
    Thread.sleep(300);
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("Torgate");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("yaboku123");
    driver.findElement(By.cssSelector(".w-100")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("tr:nth-child(1) .bi-x-circle")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector("tr:nth-child(3) .bi-x-circle")).click();
    Thread.sleep(5000);
    driver.findElement(By.cssSelector(".btn-default")).click();
    }catch (InterruptedException e) {
    	e.printStackTrace();
	}
  }
}
