package ifbp.testes.myanimelist.integration;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimeControllerTeste {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@TestConfiguration
	static class Config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("Torgate","yaboku123");
		}
	}
	
//	
//	@Test
//	public void testeHelloWorld() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/anime/f",
//				String.class)).contains("Hello, World");
//	}
	
//	@Test
//	@WithMockUser(username = "john", roles = { "VIEWER" })
//	public void givenRoleViewer_whenCallGetUsername_thenReturnUsername() {
//	    String userName = userRoleService.getUsername();
//	    
//	    assertEquals("john", userName);
//	}
//	
}
