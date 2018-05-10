package hr.tvz.pios.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import hr.tvz.pios.domain.converters.ModelToDogadajConverter;
import hr.tvz.pios.domain.converters.ModelToKorisnikConverter;

@Configuration
public class WebConfig {

	@SuppressWarnings("rawtypes")
	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<>();
		converters.add(new ModelToKorisnikConverter());
		converters.add(new ModelToDogadajConverter());
		factory.setConverters(converters);
		return factory;
	}
	
}
