package ifbp.testes.myanimelist.model;

public enum Score {

	masterpiece("(10) Obra-prima "),
	great("(9) Excelente "),
	very_good("(8) Muito Bom "),
	good("(7) Bom "),
	fine("(6) Bem "),
	avarege("(5) Mediano "),
	bad("(4) Ruim "),
	very_bad("(3) Muito Ruim "),
	horrible("(2) Horrivel "),
	appalling("(1) Terrível ");
	
	private String scoreName;
	
	Score(String scoreName){
		this.scoreName = scoreName;
	}

	public String getScoreName() {
		return scoreName;
	}
}
