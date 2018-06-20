package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.model.Attività;
import it.uniroma3.model.Azienda;
import it.uniroma3.repository.AttivitàRepository;
import it.uniroma3.repository.AziendaRepository;

public class AziendaService {
	@Autowired
	private AziendaRepository repository; 
		
		public Azienda save(Azienda azienda) {
			return this.repository.save(azienda);
		}
		
}
