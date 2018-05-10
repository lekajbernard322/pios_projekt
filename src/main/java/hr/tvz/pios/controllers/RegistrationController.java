package hr.tvz.pios.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.domain.models.KorisnikFormModel;
import hr.tvz.pios.repositories.KorisnikRepository;
import hr.tvz.pios.service.LoggingService;

@Controller
public class RegistrationController {
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LoggingService loggingService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/registracija")
	public String registracija(Model model) {
		model.addAttribute("korisnikFormModel", new KorisnikFormModel());
		return "registration/registration.html";
	}
	
	@PostMapping("/registracija")
	public String registracija(@Valid KorisnikFormModel korisnikFormModel,
			BindingResult result) {
		
		Korisnik korisnikPoKorisnickomImenu = 
				korisnikRepository.findByKorisnickoIme(korisnikFormModel.getKorisnickoIme());
		if (korisnikPoKorisnickomImenu != null) {
			result.rejectValue(
					"korisnickoIme", 
					"korisnickoIme", 
					"Korisničko ime već postoji.");
		}
		
		if (result.hasErrors()) {
			return "registration/registration.html";
		}
		
		Korisnik korisnik = 
				conversionService.convert(korisnikFormModel, Korisnik.class);
		korisnik.setLozinka(
				passwordEncoder.encode(korisnik.getLozinka()));
		korisnik.getUloge().add("ROLE_KORISNIK");
		korisnikRepository.save(korisnik);
		
		loggingService.log("korisnik " + korisnik + " se registrirao.");
		return "redirect:/prijava.html";
	}

}
