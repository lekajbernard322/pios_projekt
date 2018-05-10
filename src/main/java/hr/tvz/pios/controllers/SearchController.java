package hr.tvz.pios.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.repositories.KorisnikRepository;
import hr.tvz.pios.service.LoggingService;

@Controller
public class SearchController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LoggingService loggingService;

	@GetMapping("/searchKorisnik")
	public String searchKorisnik(Model model, Principal principal) {
		
		Korisnik korisnik =
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		List<Korisnik> iskljuceniKorisnici =
				new ArrayList<>(korisnik.getKorisniciLike());
		iskljuceniKorisnici.addAll(korisnik.getKorisniciDislike());
		
		List<Korisnik> dostupniKorisnici =
				korisnikRepository.findAllByIdNotIn(
						iskljuceniKorisnici
							.stream()
							.map(Korisnik::getId)
							.collect(Collectors.toList()));
		
		if (dostupniKorisnici.size() > 0) {
			Random random = new Random();
			Korisnik randomKorisnik = 
					dostupniKorisnici.get(
							random.nextInt(dostupniKorisnici.size()));
			
			model.addAttribute("korisnik", randomKorisnik);
		}
		
		return "search/search";
	}
	
	@PostMapping("/likeKorisnik")
	public String likeKorisnik(OdgovorModel model, Principal principal) {
		
		Korisnik korisnik =
				korisnikRepository.findByKorisnickoIme(principal.getName());
		Optional<Korisnik> likedKorisnik =
				korisnikRepository.findById(model.getId());
		
		if (likedKorisnik.isPresent() && korisnik != null) {
			korisnik.getKorisniciLike().add(likedKorisnik.get());
			korisnikRepository.save(korisnik);
		}
		
		loggingService.log("korisniku " + korisnik + " se svida " + likedKorisnik);
		return "redirect:/searchKorisnik";
	}
	
	@PostMapping("/dislikeKorisnik")
	public String dislikeKorisnik(OdgovorModel model, Principal principal) {
		
		Korisnik korisnik =
				korisnikRepository.findByKorisnickoIme(principal.getName());
		Optional<Korisnik> dislikedKorisnik =
				korisnikRepository.findById(model.getId());
		
		if (dislikedKorisnik.isPresent() && korisnik != null) {
			korisnik.getKorisniciDislike().add(dislikedKorisnik.get());
			korisnikRepository.save(korisnik);
		}

		loggingService.log("korisniku " + korisnik + " se ne svida " + dislikedKorisnik);
		return "redirect:/searchKorisnik";
	}
	
	static class OdgovorModel {
		private Integer id;
		
		public OdgovorModel() { }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
			
	}
	
}
