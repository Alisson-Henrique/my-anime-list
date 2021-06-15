package ifbp.testes.myanimelist.unidade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.repository.UserRepository;
import ifbp.testes.myanimelist.service.UserService;

@ExtendWith(SpringExtension.class)
public class UserTestMockito {

	@Spy
	User user;

	@TestConfiguration
	static class UserTestMockitoConfig {

		@Bean
		public UserService userService() {
			return new UserService();
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	void initial() {

		user.setUsername("Torgate");
		user.setPasswordUser("Arroz123");
		user.setEmail("torgatesousa@gmail.com");
		
		when(userRepository.findByEmail("torgatesousa@gmail.com")).thenReturn(user);
		when(userRepository.save(user)).thenReturn(user);
	}

	@Test
	void TestarBuscaUsuarioPorUsername() {
		when(userRepository.findByUsername("Torgate")).thenReturn(user);
 
		User res = userService.findByUsername("Torgate");
		assertThat(res.getUsername()).isEqualTo("Torgate");
		verify(userRepository, times(1)).findByUsername("Torgate");
	}
	@Test
	void TestarBuscaUsuarioPorEmail() {
		
		User user = userService.findByEmail("torgatesousa@gmail.com");
		assertThat(user.getEmail()).isEqualTo("torgatesousa@gmail.com");
		verify(userRepository, times(1)).findByEmail("torgatesousa@gmail.com");
		
	}
	
	@Test
	void TestLogin() throws Exception {
		/**
		assertThrows(Exception.class, () -> {
			userService.login(user);
		});
		**/
		verify(userRepository,times(1)).findByUsername("Torgate");

	}
	@Test
	void TestaCadastroUsuario() throws Exception {
		
		assertNotNull(userService.save(user));
		
		verify(userRepository,times(1)).save(user);
		
	}
	
	

}
