package hr.tvz.pios.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import hr.tvz.pios.PiosApplication;
import hr.tvz.pios.domain.Dogadaj;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PiosApplication.class })
public class DogadajRepositoryTest {

	@Autowired
	private DogadajRepository dogadajRepository;
	
	@Test
	public void testFindAll() throws Exception {
		List<Dogadaj> dogadaji = 
				dogadajRepository.findAll();
		assertNotNull(dogadaji);
	}
	
	@Test
	public void testFindByKorisnickoIme() throws Exception {
		Dogadaj dogadaj = 
				dogadajRepository.findByNaziv("Dogadaj1");
		assertNotNull(dogadaj);
	}
}
