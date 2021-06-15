package ifbp.testes.myanimelist.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name = "animes")
public class Anime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "anime_id")
	private Long id;
	
	@NotEmpty(message = "Deve ter um Titulo")
	private String name;
	@NotEmpty(message = "Deve ter um Descrição" )
	private String description;
	
	@NotEmpty(message = "Deve ter um studio" )
	private String studios;
	
	@NotEmpty(message = "Deve ter um Material Origina" )
	private String source;
	
	@NotEmpty(message = "Deve ter generos" )
	private String genres;
	
	@NotNull(message = "Deve ter data de Lançamento" )
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date aired;
	
	private int nbOfepisodes;
	
	private int score;
	
	private String imgPath;
	
	@Enumerated(EnumType.STRING)
	private StatusAnime statusAnime;

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public int getNbOfepisodes() {
		return nbOfepisodes;
	}

	public void setNbOfepisodes(int nbOfepisodes) {
		this.nbOfepisodes = nbOfepisodes;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anime other = (Anime) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getAired() {
		return aired;
	}

	public void setAired(Date aired) {
		this.aired = aired;
	}

	public StatusAnime getStatusAnime() {
		return statusAnime;
	}

	public void setStatusAnime(StatusAnime statusAnime) {
		this.statusAnime = statusAnime;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}	
	
}
