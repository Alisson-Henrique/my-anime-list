package ifbp.testes.myanimelist.validations;


import java.util.Date;

import ifbp.testes.myanimelist.model.Anime;
import ifbp.testes.myanimelist.model.StatusAnime;

public class AnimeValidations {

	public boolean validarNomeAnime(Anime newAnime) {
		if (newAnime.getName().length() >= 3 && newAnime.getName().length() <= 60) {
			return true;
		}
		return false;
	}


	public boolean validarStatusAnime(Anime newAnime, StatusAnime status) {
		if (status != null) {
			newAnime.setStatusAnime(status);
			return true;
		}
		return false;
	}
	
	public boolean validarEpisodiosAnime(Anime newAnime, int eps) {
		
		if(eps >= 1 && eps <= 20000) {
			newAnime.setNbOfepisodes(eps);
			return true;
		}
		
		return false;
		
	}
	
	public boolean validarDescricaoAnime(Anime newAnime, String nameAnime) {
		if (nameAnime.length() >= 10 && nameAnime.length() <= 40) {
			newAnime.setName(nameAnime);
			return true;
		}
		return false;
	}
	
	public boolean validarDataDeEstreia(Anime newAnime,Date date) {
		
		Date currentDate = new Date(System.currentTimeMillis()); 
		
		if(date.after(currentDate)) {
			if(newAnime.getStatusAnime().equals(StatusAnime.Finished_Airing) ) {
				return false;
			}
		}else if(date.before(currentDate)) {
			if(newAnime.getStatusAnime().equals(StatusAnime.Not_yet_aired)) {
				return false;
			}
		}
		
		return true;
	}
	

}
