package hr.tvz.pios.domain.converters;

import org.springframework.core.convert.converter.Converter;

import hr.tvz.pios.domain.Dogadaj;
import hr.tvz.pios.domain.models.DogadajFormModel;

public class ModelToDogadajConverter 
	implements Converter<DogadajFormModel, Dogadaj> {

	@Override
	public Dogadaj convert(DogadajFormModel model) {
		Dogadaj dogadaj = new Dogadaj();
		
		dogadaj.setId(model.getId());
		dogadaj.setNaziv(model.getNaziv());
		dogadaj.setOpis(model.getOpis());
		dogadaj.setMjesto(model.getMjesto());
		dogadaj.setDatum(model.getDatum());
		
		return dogadaj;
	}

}
