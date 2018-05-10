package hr.tvz.pios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hr.tvz.pios.domain.Log;
import hr.tvz.pios.repositories.LogRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LogRepository logRepository;
	
	@GetMapping("/logs")
	public String logs(Model model, @RequestParam Optional<String> filter) {
		
		List<Log> logsFiltered = 
				logRepository.findByLogTextContaining(filter.orElse(""));
		
		model.addAttribute("logs", logsFiltered);
		
		return "admin/logs.html";
	}
	
}
