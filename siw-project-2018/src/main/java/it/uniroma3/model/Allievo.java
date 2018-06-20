package it.uniroma3.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Allievo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(nullable=false)
	private String nome;
	@Column(nullable=false)
	private String cognome;
	private String telefono;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date data;
	private String luogoDiNascita;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Attività> attivita;
	public Allievo() {
		
	}
		public Allievo( String nome, String cognome, String telefono, String email, Date data,
			String luogoDiNascita) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.data = data;
		this.luogoDiNascita= luogoDiNascita;
	}
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}
	public void setLuogoDiNascita(String luogoDiNascità) {
		this.luogoDiNascita = luogoDiNascità;
	}
	public List<Attività> getAttività() {
		return attivita;
	}
	public void setAttività(List<Attività> attività) {
		this.attivita = attività;
	}

}
