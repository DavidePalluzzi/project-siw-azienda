package it.uniroma3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.controller.validator.CentroDiFormazioneValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attività;
import it.uniroma3.model.CentroDiFormazione;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitàService;
import it.uniroma3.service.CentroDiFormazioneService;


@Controller
public class CentroDiFormazioneController {
	public List<Allievo> allievicentro=new ArrayList<Allievo>();
	@Autowired
	private CentroDiFormazioneService service;
	@Autowired
	private AllievoService allievoservice;
	@Autowired
	private AttivitàService attivitaservice;
	@Autowired
	private CentroDiFormazioneValidator validator;
	@Autowired
	private AllievoValidator allievovalidator;

	@RequestMapping("/centri")
	public String centri(Model model) {
		model.addAttribute("centri", this.service.findAll());
		return "listaCentri";
	}

	@RequestMapping("/addCentro")
	public String addCentro(Model model) {
		model.addAttribute("centro", new CentroDiFormazione());
		return "centroForm";
	}

	@RequestMapping(value = "/centri/{id}", method = RequestMethod.GET)
	public String getCentro(@PathVariable("id") Long id, Model model,HttpSession session) {
		model.addAttribute("centro", this.service.findById(id));
		session.setAttribute("centro", service.findById(id));
		return "mostraCentro";
	}

	@RequestMapping(value = "/centri", method = RequestMethod.POST)
	public String newCentro(@Valid @ModelAttribute("centro") CentroDiFormazione centro, 
			Model model, BindingResult bindingResult) {
		this.validator.validate(centro, bindingResult);

		if (this.service.alreadyExists(centro)) {
			model.addAttribute("exists", "Centro already exists");
			return "centroForm";
		}
		else {
			if (!bindingResult.hasErrors()) {
				this.service.save(centro);
				model.addAttribute("centri", this.service.findAll());
				return "listaCentri";
			}
		}
		return "centroForm";
	}
	@RequestMapping(value ="/AllievoAttivita", method=RequestMethod.GET)
	public String getAllievo(Model model) {
		model.addAttribute("allievi", this.allievoservice.findAll());
		return "allieviTotali";
	}
	@RequestMapping(value ="/ShowAttivita/{id}", method=RequestMethod.GET)
	public String ShowAttivita(Model model,@PathVariable("id") Long id,HttpSession session) {
		session.setAttribute("allievo", allievoservice.findById(id));
		CentroDiFormazione centro=(CentroDiFormazione) session.getAttribute("centro");
		model.addAttribute("attivita", centro.getAttivita());
		System.out.println("centro di formazione in sessione "+centro.getId());
		return "listaAttivitaCentro";
	}
	@RequestMapping(value ="/AggiungiAllievoAttivita/{id}")
	public String addAllievoAttivita(Model model,@PathVariable("id") Long id,HttpSession session) {
		Allievo allievo= (Allievo) session.getAttribute("allievo");
		Attività attivita= attivitaservice.findById(id);
		allievo.getAttività().add(attivita);
		attivita.getAllievi().add(allievo);

		return "index";
	}


}
