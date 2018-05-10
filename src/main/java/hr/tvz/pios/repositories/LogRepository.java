package hr.tvz.pios.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import hr.tvz.pios.domain.Log;

@Repository
public interface LogRepository extends MongoRepository<Log, Integer> {
	
	List<Log> findByLogTextContaining(String searchString);
	
}
