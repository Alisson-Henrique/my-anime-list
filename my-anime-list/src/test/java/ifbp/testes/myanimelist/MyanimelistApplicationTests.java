package ifbp.testes.myanimelist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.AnimesUser;
import ifbp.testes.myanimelist.model.User;
import ifbp.testes.myanimelist.repository.AnimeRepository;
import ifbp.testes.myanimelist.repository.AnimesUserRepository;
import ifbp.testes.myanimelist.repository.UserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MyanimelistApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AnimesUserRepository animesUserRepository;
	
//	@Test
//	void TesteCadastroUser() throws ParseException {
//		
//		User user = new User();
//		
//		user.setUsername("Torgate");
//		user.setEmail("torgatehenrique@gmail.com");
//		user.setPasswordUser("$2a$10$gg6jbwSRQZ4isqb3it235u/oAUVlttuJpglQ3HhTL.Xw.5TasxHR2");
//		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
//		Date date = sdf.parse("03/07/2000");
//		user.setBirthDate(date);
//		
//		assertNotNull(userRepository.save(user));
//		
//	}
//	@Test
//	void procurarUserPorNome() {
//		
//		String username = "Torgate";
//		
//		User user = userRepository.findByUsername("Torgate");
//		
//		assertThat(user.getUsername()).isEqualTo(username);
//	}

	
//	@Test
//	void testeAnimesUser() {
//		
//		AnimesUser animeuser = new AnimesUser();
//		
//		animeuser.setComment("Lixo");
//		animeuser.setUsername("Torgate");
//		animeuser.setAnime("Boruto");
//		
//		assertNotNull(animesUserRepository.save(animeuser));
//		
//		assertEquals(1, animesUserRepository.findByUsernameContaining("Torgate").size());
//		
//		AnimesUser animeuser2 = new AnimesUser();
//		
//		animeuser2.setComment("Mais ou menos");
//		animeuser2.setUsername("Gatetor");
//		animeuser2.setAnime("Boruto");
//		animesUserRepository.save(animeuser2);
//		
//		animesUserRepository.deleteInBatch(animesUserRepository.findByUsernameContaining("Torgate"));
//		
//		
//	}
//	

}
