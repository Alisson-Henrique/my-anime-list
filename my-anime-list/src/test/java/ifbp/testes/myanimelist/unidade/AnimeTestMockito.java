package ifbp.testes.myanimelist.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.repository.AnimeRepository;
import ifbp.testes.myanimelist.service.AnimeService;
import ifbp.testes.myanimelist.validations.AnimeValidations;


@ExtendWith(SpringExtension.class)
public class AnimeTestMockito {

	@Spy
	Anime anime;
	
	AnimeValidations validation = spy(AnimeValidations.class);
	
	@Spy
	Page<Anime> animes;
	
	@TestConfiguration
	static class AnimeTestMockitoConfig {

		@Bean
		public AnimeService animeService() {
			return new AnimeService();
		}
	}
	
	@Autowired
	private AnimeService animeService;
	
	@MockBean
	private AnimeRepository animeRepository;
	
	@BeforeEach
	void initial() {
		
		when(animeRepository.save(anime)).thenReturn(anime);
	}
	
	
	@Test
	void procurarAnimePorId() throws Exception {
		Long id = 1l;
		
		anime.setName("Hunter x Hunter");
		
		when(animeRepository.findById(id)).thenReturn(Optional.of(anime));
		
		Anime reponse = animeService.findById(1l);
		
		Assertions.assertThat(reponse.getName()).isEqualTo("Hunter x Hunter");
		
	}
	@Test
	void TestaCadastroAnime() throws Exception {
		
		assertNotNull(animeService.save(anime));
		
		verify(animeRepository,times(1)).save(anime);
		
	}
	
	@Test
	void TestaGetAnimeList() {
		
		when(animeRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC,"name")))).thenReturn(animes);
		
		assertEquals(0, animeService.getPageFiveAnimes().size());
		
		verify(animeRepository,times(1)).findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC,"name")));
	}
	
	
	
	
}
