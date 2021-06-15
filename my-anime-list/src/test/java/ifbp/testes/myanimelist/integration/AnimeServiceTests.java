package ifbp.testes.myanimelist.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.StatusAnime;
import ifbp.testes.myanimelist.service.AnimeService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AnimeServiceTests {

	@Autowired
	private AnimeService animeService;
	
	@TestConfiguration
	static class AnimeTestMockitoConfig {

		@Bean
		public AnimeService animeService() {
			return new AnimeService();
		}
	}
	
	@Test
	void TesteBuscarPorId() {
		
		assertNotNull(animeService.findById(1l));
	}
	
	@Test
	void TesteBuscarPorNome() {
		
		assertNotNull(animeService.findByName("Soul Eater"));
	}
	
	@Test
	void TesteCadastrarAnime() throws ParseException {
		
		Anime anime = new Anime();
		anime.setName("Hunter x Hunter");
		anime.setDescription("Um anime de aventura");
		anime.setNbOfepisodes(148);
		anime.setSource("Mangá");
		anime.setStatusAnime(StatusAnime.Finished_Airing);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse("03/03/2000");
		anime.setAired(date);
		anime.setGenres("Aventura, Shounen");
		anime.setStudios("MadHouse");
		anime.setImgPath("33657.webp");
		
		assertNotNull(animeService.save(anime));
	}
	
	@Test
	void TesteGetAnime() {
		
		assertEquals(5,animeService.getPageAnimesOderByName(0, 5).size());
	}
	
//	@Test
//	void TesteDeleteAnime() {
//		
//		//animeService.deleteAnimeById(17l);
//		
//		//assertThat(anime)
//	}
}
