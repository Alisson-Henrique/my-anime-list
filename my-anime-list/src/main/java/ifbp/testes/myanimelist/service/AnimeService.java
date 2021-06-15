package ifbp.testes.myanimelist.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.repository.AnimeRepository;
import ifbp.testes.myanimelist.repository.AnimesUserRepository;


@Service
public class AnimeService {

	@Autowired
	private AnimeRepository animeRepository;
	
	@Autowired
	private AnimesUserService animesUserService;

	public Anime save(Anime anime) {

		return animeRepository.save(anime);
	}

	public Anime findById(Long id) {

		Optional<Anime> anime = animeRepository.findById(id);

		return anime.get();
	}

	public void deleteAnimeById(Long id) throws Exception{

		Anime anime = findById(id);

		animesUserService.deleteListEvaliationsByAnimeName(anime.getName());
		
		animeRepository.delete(anime);

	}

	public List<Anime> getPageFiveAnimes() {

		Page<Anime> page = animeRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC,"name")));


		List<Anime> list = page.getContent();


		return list;
	}

	public List<Anime> getPageAnimesOderByName(int pages, int elements){

		Page<Anime> page = animeRepository.findAll(PageRequest.of(pages, elements, Sort.by(Sort.Direction.ASC,"name")));


		List<Anime> list = page.getContent();
		
		return list;
	}


	public Anime getOne(long id) {

		return animeRepository.getOne(id);
	}

	public Anime findByName(String name) {
		return animeRepository.findByName(name);
	}
}
