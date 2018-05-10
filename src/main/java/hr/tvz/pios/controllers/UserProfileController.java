package hr.tvz.pios.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.repositories.KorisnikRepository;
import hr.tvz.pios.service.FileStorageService;
import hr.tvz.pios.service.LoggingService;

@Controller
public class UserProfileController {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LoggingService loggingService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/profile")
	public String profileDetails(Model model, Principal principal) {
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
		
		model.addAttribute("korisnik", korisnik);
		
		loggingService.log("korisnik " + korisnik + " je otvorio svoj profil.");
		
		return "profile/profileDetail.html";
	}
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("file") MultipartFile file, 
			Principal principal) throws Exception {
		
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(principal.getName());
		Integer korisnikId = korisnik.getId();
		
		fileStorageService.store(file, korisnikId);
		
		korisnik.getSlikeUrl().add(file.getOriginalFilename());
		korisnikRepository.save(korisnik);
		
		return "redirect:/profile";
	}
	
	@GetMapping(value = "/getImage")
    @ResponseBody
    public byte[] getImage(LoadImageModel model) throws IOException {
    	Path imagePath = fileStorageService.load(
				model.getFilename(), 
				model.getId());
    	
    	loggingService.log("File " + model.getFilename() 
    		+ " requested by user with id " + model.getId());
    	return Files.readAllBytes(imagePath);
    }
	
	static class LoadImageModel {
		
		private String filename;
		
		private Integer id;
		
		public LoadImageModel() { }

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
	}

}
