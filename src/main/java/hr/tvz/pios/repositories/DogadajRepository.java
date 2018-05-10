package hr.tvz.pios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.pios.domain.Dogadaj;

public interface DogadajRepository extends JpaRepository<Dogadaj, Integer> {
	
	Dogadaj findByNaziv(String naziv);
	
}
