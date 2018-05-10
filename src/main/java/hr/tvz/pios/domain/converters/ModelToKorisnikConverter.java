package hr.tvz.pios.domain.converters;

import org.springframework.core.convert.converter.Converter;

import hr.tvz.pios.domain.Korisnik;
import hr.tvz.pios.domain.models.KorisnikFormModel;

public class ModelToKorisnikConverter 
	implements Converter<KorisnikFormModel, Korisnik> {
	
	@Override
	public Korisnik convert(KorisnikFormModel model) {
		Korisnik korisnik = new Korisnik();
		
		korisnik.setId(model.getId());
		korisnik.setKorisnickoIme(model.getKorisnickoIme());
		korisnik.setIme(model.getIme());
		korisnik.setPrezime(model.getPrezime());
		korisnik.setGodine(model.getGodine());
		korisnik.setMjesto(model.getMjesto());
		korisnik.setOpis(model.getOpis());
		korisnik.setSpol(model.getSpol());
		korisnik.setTrazeniSpol(model.getTrazeniSpol());
		korisnik.setLozinka(model.getLozinka());
		
		return korisnik;
	}

}
