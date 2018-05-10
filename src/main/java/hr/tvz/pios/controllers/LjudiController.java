package hr.tvz.pios.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.repositories.KorisnikRepository;

@Controller
public class LjudiController {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@GetMapping("/likedList")
	public String likedList(Model model, Principal principal) {
		
		Korisnik korisnik = 
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		model.addAttribute("korisnik", korisnik);
		
		return "ljudi/likedList";
	}
	
	@GetMapping("/notLikedList")
	public String notLikedList(Model model, Principal principal) {

		Korisnik korisnik = 
				korisnikRepository.findByKorisnickoIme(principal.getName());
		
		model.addAttribute("korisnik", korisnik);
		
		return "ljudi/notLikedList";
	}
	
}
