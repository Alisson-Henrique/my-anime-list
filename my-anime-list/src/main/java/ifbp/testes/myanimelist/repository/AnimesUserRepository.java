package ifbp.testes.myanimelist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifbp.testes.myanimelist.model.AnimesUser;

@Repository
public interface AnimesUserRepository extends JpaRepository<AnimesUser, Long>{

	List<AnimesUser> findByUsernameContaining(String username);
	
	List<AnimesUser> findByAnimeAndUsernameContaining(String animeName,String userName);
	
	List<AnimesUser> findByAnimeContaining(String animeName);
	
	AnimesUser findByAnime(String animeName);
}
