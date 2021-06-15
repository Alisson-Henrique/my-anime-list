package ifbp.testes.myanimelist.model;

public enum State {
	watching("Assistindo"),
	completado("Completado"),
	on_hold("Pausado"),
	dropped("Desistiu"),
	plan_to_wacth("Pretendo assistir");
	
	private String stateName;
	
	State(String stateName) {
		this.stateName = stateName;
	}
	
	public String getStateName() {
		return stateName;
	}
}
