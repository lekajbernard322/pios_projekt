package hr.tvz.pios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.tvz.pios.domain.Log;
import hr.tvz.pios.repositories.LogRepository;

@Service
public class LoggingService {

	@Autowired
	private LogRepository logRepository;
	
	public boolean log(String logText) {
		Log log = new Log();
		log.setLogText(logText);
		logRepository.save(log);
		return true;
	}
	
}
