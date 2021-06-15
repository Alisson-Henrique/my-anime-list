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
public class EditandoAnimeTest {
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
  public void editandoAnime() {
    driver.get("http://localhost:8080/entrar");
    driver.manage().window().setSize(new Dimension(1050, 708));
    
    
    try {
    Thread.sleep(200);
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("Torgate");
    driver.findElement(By.cssSelector("form")).click();
    Thread.sleep(200);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("yaboku123");
    driver.findElement(By.cssSelector(".w-100")).click();
    Thread.sleep(2000);
    
    
    
    
    driver.findElement(By.cssSelector("tr:nth-child(4) .bi-pencil-square")).click();
    Thread.sleep(2000);
    
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Hunter x Hunter (2011)");
    
    
    driver.findElement(By.id("nbOfepisodes")).click();
    driver.findElement(By.id("nbOfepisodes")).clear();
    driver.findElement(By.id("nbOfepisodes")).sendKeys("148");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Lista de Animes")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector(".btn-default")).click();
    Thread.sleep(2000);
    }catch (InterruptedException e) {
    	e.printStackTrace();
	}
  }
}
