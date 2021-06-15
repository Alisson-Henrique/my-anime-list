package ifbp.testes.myanimelist.model;

public enum StatusAnime {

	Finished_Airing("Fim da exibição"),
	Currently_Airing("Atualmente sendo exibido"),
	Not_yet_aired("Ainda não foi ao ar");
	
	private String statusString;
	
	StatusAnime(String statusString){
		this.statusString = statusString;
	}

	public String getStatusString() {
		return statusString;
	}
}
