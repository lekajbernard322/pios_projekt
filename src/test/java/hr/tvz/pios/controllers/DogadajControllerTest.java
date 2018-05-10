package hr.tvz.pios.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.pios.PiosApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { PiosApplication.class })
public class DogadajControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Transactional
	public void testDogadaji() throws Exception {
		mvc.perform(get("/dogadaji").with(user("korisnik1").password("password").roles("USER", "ADMIN")))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testDetalji() throws Exception {
		mvc.perform(get("/detalji").with(user("korisnik1").password("password").roles("USER", "ADMIN")))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testNoviDogadaj() throws Exception {
		mvc.perform(get("/noviDogadaj").with(user("korisnik1").password("password").roles("USER", "ADMIN")))
			.andExpect(status().isOk());
	}
}
