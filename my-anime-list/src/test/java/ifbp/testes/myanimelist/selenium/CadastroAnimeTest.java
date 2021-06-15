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
public class CadastroAnimeTest {
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
  public void cadastroAnime() {//CADASTRO SEM ERROS FLUXO NORMAL
    driver.get("http://localhost:8080/entrar");
    driver.manage().window().setSize(new Dimension(1050, 708));
    try {
    Thread.sleep(100);
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("Torgate");
    Thread.sleep(100);
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("yaboku123");
    Thread.sleep(100);
    driver.findElement(By.cssSelector(".w-100")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("li:nth-child(1) > a")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("Hunter x Hunter");
    Thread.sleep(100);
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).sendKeys("Monstros terríveis… Criaturas exóticas… Imensas riquezas… Tesouros escondidos… Enclaves malignos… Terras inexploradas… A palavra “desconhecido” guarda magia. E pessoas incríveis são atraídas para essa magia. Elas são conhecidas como… Hunters!");
    Thread.sleep(100);
    driver.findElement(By.id("genres")).click();
    driver.findElement(By.id("genres")).sendKeys("Action, Adventure, Fantasy, Shounen, Super Power.");
    Thread.sleep(100);
    driver.findElement(By.id("source")).click();
    driver.findElement(By.id("source")).sendKeys("Mangá");
    Thread.sleep(100);
    driver.findElement(By.id("aired")).click();
    driver.findElement(By.id("aired")).sendKeys("02/10/2011");
    Thread.sleep(100);
    driver.findElement(By.id("nbOfepisodes")).click();
    driver.findElement(By.id("nbOfepisodes")).sendKeys("140");
    Thread.sleep(100);
    driver.findElement(By.id("studios")).click();
    driver.findElement(By.id("studios")).sendKeys("Mad House");
   // Thread.sleep(100);
    //driver.findElement(By.id("file")).click();
   // driver.findElement(By.id("file")).sendKeys("C:\\fakepath\\33657.webp");
    Thread.sleep(100);
    driver.findElement(By.cssSelector(".btn-primary")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Lista de Animes")).click();
    Thread.sleep(4000);
    driver.findElement(By.cssSelector("tr:nth-child(3) .bi-pencil-square")).click();
    driver.findElement(By.linkText("Lista de Animes")).click();
    }catch (InterruptedException e) {
    	e.printStackTrace();
	}
  }
  
  @Test
  public void cadastroAnimeSemOsCamposPreenchidos() {//CADASTRO CAMPOS VAZIOS
	  
	  driver.get("http://localhost:8080/entrar");
	    driver.manage().window().setSize(new Dimension(1050, 708));
	    
	    try {
	    	
	    //LOGIN
	    Thread.sleep(100);
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("Torgate");
	    Thread.sleep(100);
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("yaboku123");
	    Thread.sleep(100);
	    driver.findElement(By.cssSelector(".w-100")).click();
	    Thread.sleep(2000);
	    
	    //CADASTRO DE ANIME SO COM CAMPO NUMERO DE EPISODIOS
	    driver.findElement(By.cssSelector("li:nth-child(1) > a")).click();
	    Thread.sleep(2000);  
	    driver.findElement(By.id("nbOfepisodes")).click();
	    driver.findElement(By.id("nbOfepisodes")).sendKeys("62");
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    Thread.sleep(2000);
	    
	    
	    //FALTA NOME DO ANIME
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).sendKeys("Um garoto desafiara e lutara contra o imperio de Britania.");
	    Thread.sleep(100);
	    driver.findElement(By.id("genres")).click();
	    driver.findElement(By.id("genres")).sendKeys("Action, Adventure, Super Power.");
	    Thread.sleep(100);
	    driver.findElement(By.id("source")).click();
	    driver.findElement(By.id("source")).sendKeys("Mangá");
	    Thread.sleep(100);
	    driver.findElement(By.id("aired")).click();
	    driver.findElement(By.id("aired")).sendKeys("05/05/2007");
	    Thread.sleep(100);
	    driver.findElement(By.id("studios")).click();
	    driver.findElement(By.id("studios")).sendKeys("Mad House");
	    Thread.sleep(5000);
	    
	   // Thread.sleep(100);
	    //driver.findElement(By.id("file")).click();
	   // driver.findElement(By.id("file")).sendKeys("C:\\fakepath\\33657.webp");
	    driver.findElement(By.id("name")).click();
	    driver.findElement(By.id("name")).sendKeys("Code Geass");
	    
	    Thread.sleep(5000);
	    driver.findElement(By.cssSelector(".btn-primary")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Lista de Animes")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("tr:nth-child(3) .bi-pencil-square")).click();
	    driver.findElement(By.linkText("Lista de Animes")).click();
	    }catch (InterruptedException e) {
	    	e.printStackTrace();
		}
	  
	  
	  
	  
  }
}
