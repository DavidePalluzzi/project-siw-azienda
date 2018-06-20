package it.uniroma3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Azienda {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="azienda")
	private List<CentroDiFormazione> centri;
	
	@OneToMany
	private List<Allievo> allievi;
	
	public Azienda () {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CentroDiFormazione> getCentri() {
		return centri;
	}
	public void setCentri(List<CentroDiFormazione> centri) {
		this.centri = centri;
	}
	public List<Allievo> getAllievi() {
		return allievi;
	}
	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}
	
}
