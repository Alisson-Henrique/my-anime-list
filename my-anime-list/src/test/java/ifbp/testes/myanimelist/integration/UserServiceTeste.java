package ifbp.testes.myanimelist.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.service.UserService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTeste {

	@Autowired
	private UserService userService;
	
	@TestConfiguration
	static class AnimeTestMockitoConfig {

		@Bean
		public UserService userService() {
			return new UserService();
		}
	}
	
	@Test
	void TesteBuscarUsuarioPorId() throws Exception {
		
		assertEquals(1,userService.findById(1l).getId());
	}
	
	@Test
	void TesteBuscarUsuarioPorNome() {
		
		assertThat(userService.findByUsername("Gatetor").getUsername()).isEqualTo("Gatetor");
	}
	
	@Test
	void TesteCadastrarUsuario() throws Exception {
		
		User user = new User();
		user.setEmail("joaosilva@gmail.com");
		user.setPasswordUser("dado123");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("10/05/1999");
		user.setBirthDate(date);
		user.setUsername("Joao");
		
		assertNotNull(userService.save(user));
		
	}
//	@Test
//	void TesteDeletarUser() throws Exception {
//		
//		
//		
//		userService.deleteUserById(2l);
//		
//		
//	}
	
	
	
	
}
