package ifbp.testes.myanimelist.selenium;



import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest

public class SeleniumTestes {

	
	
	private static WebDriver driver;
	
	
	@BeforeClass
	public static void config() {
		System.setProperty("WebDriver.edge.driver","C:\\Users\\torga\\Desktop\\Arquivos-C\\Projetos\\Projeto Testes\\myanimelist\\drivers\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
	}
	@org.junit.Test
	public void testenav() throws InterruptedException {
		
		driver.get("http://localhost:8080");
		java.lang.Thread.sleep(2000);
	}
	
	
	
	@AfterClass
	public static void finalizar() {
		driver.quit();
	}
	

	
	
}
