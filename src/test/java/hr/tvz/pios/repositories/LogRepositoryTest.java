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
import hr.tvz.pios.domain.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PiosApplication.class })
public class LogRepositoryTest {
	
	@Autowired
	private LogRepository logRepository;
	
	@Test
	public void testFindAll() throws Exception {
		List<Log> logs = 
				logRepository.findAll();
		assertNotNull(logs);
	}

}
