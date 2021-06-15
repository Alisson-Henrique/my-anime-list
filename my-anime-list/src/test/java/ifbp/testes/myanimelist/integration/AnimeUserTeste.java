package ifbp.testes.myanimelist.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import ifbp.testes.myanimelist.model.AnimesUser;
import ifbp.testes.myanimelist.model.Score;
import ifbp.testes.myanimelist.model.State;
import ifbp.testes.myanimelist.repository.AnimeRepository;
import ifbp.testes.myanimelist.service.AnimeService;
import ifbp.testes.myanimelist.service.AnimesUserService;


import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AnimeUserTeste {

	@Autowired
	private AnimesUserService animesUserService;
	
	@MockBean
	private AnimeRepository animeRepository;
	
	
	@TestConfiguration
	static class AnimeTestMockitoConfig {

		@Bean
		public AnimesUserService animesUserService() {
			return new AnimesUserService();
		}
	}
	
	@Test
	public void TesteGetAnimesUser() {
		
	assertEquals(1, animesUserService.findByAnimeAndUserContaining("Violet Evergarden", "Gatetor").size());
	assertThat(animesUserService.findByAnimeAndUserContaining("Violet Evergarden", "Gatetor").get(0).getAnime()).isEqualTo("Violet Evergarden");
	assertThat(animesUserService.findByAnimeAndUserContaining("Violet Evergarden", "Gatetor").get(0).getUsername()).isEqualTo("Gatetor");
	}
	
	@Test
	public void TesteSaveAnimesUser() {
		
	AnimesUser animesUser = new AnimesUser();
	animesUser.setAnime("Hunter x Hunter");
	animesUser.setUsername("Killmor");
	animesUser.setComment("O melhor Anime do Mundo!!");
	animesUser.setScore(Score.masterpiece);
	animesUser.setState(State.completado);
	
	assertNotNull(animesUserService.save(animesUser));
	assertThat(animesUserService.findByAnimeAndUserContaining("Hunter x Hunter", "Killmor").get(0).getAnime()).isEqualTo("Hunter x Hunter");
	assertThat(animesUserService.findByAnimeAndUserContaining("Hunter x Hunter", "Killmor").get(0).getUsername()).isEqualTo("Killmor");
	}
	
	@Test
	void TesteAnimesUserListarAnimes() {
		
		assertEquals(2,animesUserService.getAnimesUser("Gatetor").size());
	}
	
	@Test
	void TesteDeleteAnimesUser() {
		
		assertEquals(1,animesUserService.getAnimesUser("Maria").size());
		
		animesUserService.deleteListEvaliationByAnimeNameAndUsername("Fullmetal Alchemist: Brotherhood", "Maria");
		
		assertNotEquals(1,animesUserService.getAnimesUser("Maria").size());
	}
	
//	@Test
//	void TesteDeleteAnime() throws Exception {
//		
//		when(animeRepository.delete(null));
//		
//		animeService.deleteAnimeById(6l);
//		
//		
//	}
	
}
