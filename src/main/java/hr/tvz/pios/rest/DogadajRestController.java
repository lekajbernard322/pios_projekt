package hr.tvz.pios.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.tvz.pios.domain.Dogadaj;
import hr.tvz.pios.repositories.DogadajRepository;

@RestController
@RequestMapping("/api")
public class DogadajRestController {

	@Autowired
	private DogadajRepository dogadajRepository;
	
	@GetMapping("/dohvatiDogadaje")
	public List<Dogadaj> dohvatiDogadaje() {
		return dogadajRepository.findAll();
	}
	
	@GetMapping("/dohvatiDogadaj/{id}")
	public Dogadaj dohvatiDogadaj(@PathVariable("id") Integer id) {
		return dogadajRepository.findById(id).orElse(null);
	}
	
	@DeleteMapping("/obrisiDogadaj/{id}")
	public ResponseEntity<?> obrisiDogadaj(@PathVariable("id") Integer id) {
		try {
			dogadajRepository.deleteById(id);
			
			return new ResponseEntity<>(
					Collections.singletonMap("status", "deleted succesfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("status", e.getLocalizedMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
}
