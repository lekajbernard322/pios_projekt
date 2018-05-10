package hr.tvz.pios.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.pios.domain.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	Korisnik findByKorisnickoIme(String korisnickoIme);
	
	List<Korisnik> findAllByIdNotIn(List<Integer> iskljuceniKorisniciId);

	Optional<Korisnik> findById(Integer id);

}
