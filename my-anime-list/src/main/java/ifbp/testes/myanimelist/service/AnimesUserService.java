package ifbp.testes.myanimelist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifbp.testes.myanimelist.model.AnimesUser;
import ifbp.testes.myanimelist.repository.AnimesUserRepository;

@Service
public class AnimesUserService {

	@Autowired
	private AnimesUserRepository animesUserRepository;

	public List<AnimesUser> getAnimesUser(String username) {

		return animesUserRepository.findByUsernameContaining(username);
	}
	
	public AnimesUser update(long id,AnimesUser update) {
		
		AnimesUser animeOld = findById(id);
		
		animeOld.setAnime(update.getAnime());
		animeOld.setComment(update.getComment());
		animeOld.setScore(update.getScore());
		animeOld.setState(update.getState());
		
		
		return save(animeOld);
	}

	public AnimesUser save(AnimesUser animeUser) {

		return animesUserRepository.save(animeUser);
	}

	public List<AnimesUser> findByAnimeAndUserContaining(String animeName,String userName) {

		return animesUserRepository.findByAnimeAndUsernameContaining(animeName, userName);
	}


	public void deleteListEvaliationsByAnimeName(String animeName) {

		List<AnimesUser> animesUsers = animesUserRepository.findByAnimeContaining(animeName);

		animesUserRepository.deleteInBatch(animesUsers);
	}

	public void deleteListEvaliationByAnimeNameAndUsername(String animeName,String userName) {

		List<AnimesUser> animesUsers = animesUserRepository.findByAnimeAndUsernameContaining(animeName, userName);

		animesUserRepository.deleteInBatch(animesUsers);
	}
	
	public void deleteById(long id) {
		
		animesUserRepository.deleteById(id);
	}
	public AnimesUser findAnimesUserByAnime(String animeName) {
		
		return animesUserRepository.findByAnime(animeName);
	}
	public AnimesUser findById(long id) {
		return animesUserRepository.findById(id).get();
	}

}
