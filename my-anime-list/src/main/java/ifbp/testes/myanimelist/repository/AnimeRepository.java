package ifbp.testes.myanimelist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import ifbp.testes.myanimelist.model.Anime;


@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long>{

	Anime findByName(String name);
}
