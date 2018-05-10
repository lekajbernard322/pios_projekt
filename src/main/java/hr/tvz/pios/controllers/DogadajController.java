package hr.tvz.pios.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.tvz.pios.domain.Dogadaj;
import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.domain.models.DogadajFormModel;
import hr.tvz.pios.repositories.DogadajRepository;
import hr.tvz.pios.repositories.KorisnikRepository;
import hr.tvz.pios.service.LoggingService;

@Controller
public class DogadajController {
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private DogadajRepository dogadajRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LoggingService loggingService;
	
	@GetMapping("/dogadaji")
	public String dogadaji(Model model, Principal principal) {
		List<Dogadaj> dogadaji = dogadajRepository.findAll();
		Korisnik korisnik = 
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		model.addAttribute("korisnik", korisnik);
		model.addAttribute("dogadaji", dogadaji);
		
		return "dogadaj/lista.html";
	}
	
	@GetMapping("/detalji")
	public String detalji(@RequestParam("id") Optional<Integer> id, Model model,
			Principal principal) {
		if (id.isPresent()) {
			Optional<Dogadaj> dogadaj = dogadajRepository.findById(id.get());
			if (dogadaj.isPresent()) {
				Korisnik korisnik = 
						korisnikRepository.findByKorisnickoIme(principal.getName());
				
				model.addAttribute("korisnik", korisnik);
				model.addAttribute("dogadaj", dogadaj.get());
				return "dogadaj/detalji.html";
			}
		}
		
		return "redirect:/dogadaji";
	}
	
	@GetMapping("/noviDogadaj")
	public String noviDogadaj(Model model) {
		
		model.addAttribute("dogadajFormModel", new DogadajFormModel());
		
		return "dogadaj/form.html";
	}
	
	@PostMapping("/noviDogadaj")
	public String noviDogadaj(@Valid DogadajFormModel dogadajFormModel,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "dogadaj/form.html";
		}
		
		Dogadaj dogadaj =
				conversionService.convert(dogadajFormModel, Dogadaj.class);
		dogadajRepository.save(dogadaj);
		
		loggingService.log("stvoren novi dogadaj " + dogadaj);
		return "redirect:/dogadaji";
	}
	
	@PostMapping("/prijaviDolazak")
	public String prijaviDolazak(PrijaviDolazakModel model,
			Principal principal) {
		
		Korisnik korisnik = 
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		Optional<Dogadaj> dogadaj = 
				dogadajRepository.findById(model.getId());
		
		if (dogadaj.isPresent() && korisnik != null) {
			dogadaj.get().getKorisnici().add(korisnik);
			dogadajRepository.save(dogadaj.get());
			
			loggingService.log("korisnik " + korisnik + " je prijavio dolazak na dogadaj " + dogadaj);
			return "redirect:/dogadaji";
		}
		
		return "redirect:/dogadaji";
	}
	
	@PostMapping("/odjaviDolazak")
	public String odjaviDolazak(PrijaviDolazakModel model,
			Principal principal) {
		
		Korisnik korisnik = 
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		Optional<Dogadaj> dogadaj = 
				dogadajRepository.findById(model.getId());
		
		if (dogadaj.isPresent() && korisnik != null) {
			dogadaj.get().getKorisnici().remove(korisnik);
			dogadajRepository.save(dogadaj.get());

			loggingService.log("korisnik " + korisnik + " je odjavio dolazak na dogadaj " + dogadaj);
			return "redirect:/dogadaji";
		}
		
		return "redirect:/dogadaji";
	}
	
	static class PrijaviDolazakModel {
		private Integer id;
		
		public PrijaviDolazakModel() { }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
	}
	
}
