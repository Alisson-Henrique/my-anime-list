package ifbp.testes.myanimelist.unidade;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.StatusAnime;
import ifbp.testes.myanimelist.validations.AnimeValidations;


@ExtendWith(MockitoExtension.class)
public class AnimeTests {

	@Spy
	Anime anime;

	@Spy
	AnimeValidations  animeValidations;
	
	
	@Test
	void TesteLimiteEntradaDeNomeAnime() {

		String nome = "ds";
		
		anime.setName(nome);
		assertFalse(animeValidations.validarNomeAnime(anime));
		verify(anime).setName(nome);
		
		
		nome = "Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen 3rd Season";
		anime.setName(nome);
		assertFalse(animeValidations.validarNomeAnime(anime));
		
		verify(animeValidations,times(2)).validarNomeAnime(anime);
		
	}

	@Test
	void TesteDataAnime() throws ParseException {
		
		anime.setStatusAnime(StatusAnime.Finished_Airing);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = sdf.parse("03/03/2000");
		assertTrue(animeValidations.validarDataDeEstreia(anime, date));
		
		
		date = sdf.parse("10/02/2030");
		assertFalse(animeValidations.validarDataDeEstreia(anime, date));
		
		
		anime.setStatusAnime(StatusAnime.Not_yet_aired);
		date = sdf.parse("02/05/2005");
		assertFalse(animeValidations.validarDataDeEstreia(anime, date));
		verify(animeValidations,times(1)).validarDataDeEstreia(anime,date);
			
	}
	
	@Test
	void TesteAnimeSemStatus() {
		
		assertFalse(animeValidations.validarStatusAnime(anime, null));
		verify(animeValidations,times(1)).validarStatusAnime(anime,null);
	}
	
	@Test
	void TesteLimititeEntradaEpisodiosAnime() {
		
		assertTrue(animeValidations.validarEpisodiosAnime(anime, 1));
		
		assertFalse(animeValidations.validarEpisodiosAnime(anime, 500000));
	}
	
}
