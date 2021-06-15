package ifbp.testes.myanimelist.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {


	@LocalServerPort
	private int port;
	
	
	@Autowired
    private TestRestTemplate template;


//    @Test
//    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
//        ResponseEntity<String> result = template.withBasicAuth("Torgate", "yaboku123")
//          .getForEntity("/anime/newAnime", String.class);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }
//    
//    @Test
//	public void greetingShouldReturnDefaultMessage() throws Exception {
//		assertThat(this.template.getForObject("http://localhost:" + port + "/anime/f",
//				String.class)).contains("Hello, World");
//	}
}
