package hr.tvz.pios.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	  
	  private final Path rootLocation = Paths.get("uploads");

	  public void store(MultipartFile file, Integer id) throws Exception {
		  String filename = StringUtils.cleanPath(file.getOriginalFilename());
	    
		  try {
			  if (file.isEmpty()) {
				  throw new Exception("Failed to store empty file " + filename);
			  }
	      
			  if (filename.contains("..")) {
				  throw new Exception(
						  "Cannot store file with relative path." + filename);
			  }
	      
			  Path userDirectoryPath = rootLocation.resolve(id.toString());
			  Files.createDirectories(userDirectoryPath);
	      
			  Files.copy(
					  file.getInputStream(), 
					  rootLocation.resolve(id.toString()).resolve(filename),
					  StandardCopyOption.REPLACE_EXISTING);
		  } catch (IOException e) {
			  throw new Exception("Failed to store file " + filename);
		  }
	  }

	  public Path load(String filename, Integer id) {
		  return rootLocation.resolve(id.toString()).resolve(filename);
	  }

	  public Resource loadAsResource(String filename, Integer id) throws Exception {
		  try {
			  Path file = load(filename, id);
			  Resource resource = new UrlResource(file.toUri());
			  if (resource.exists() || resource.isReadable()) {
				  return resource;
			  } else {
				  throw new Exception("Failed to find file " + filename);
			  }
		  } catch (MalformedURLException e) {
			  throw new Exception("Could not read file " + filename);
		  }
	  }

	  public Set<String> loadFilenames(Integer id) throws Exception {
		  try {
			  return Files.walk(this.rootLocation.resolve(id.toString()), 1)
					  .filter(path -> !path.equals(this.rootLocation.resolve(id.toString())))
					  .map(path -> this.rootLocation.resolve(id.toString()).relativize(path).toString())
					  .collect(Collectors.toSet());
		  } catch (IOException e) {
			  throw new Exception("Failed to read stored files.", e);
		  }
	  }

	  @PostConstruct
	  public void init() throws Exception {
		  try {
			  Files.createDirectories(rootLocation);
		  } catch (IOException e) {
			  throw new Exception("Failed to initialize upload root directory.", e);
		  }
	  }

	  public void delete(String filename, Integer id) throws Exception {
		  try {
			  Files.delete(rootLocation.resolve(id.toString()).resolve(filename));
		  } catch (NoSuchFileException | DirectoryNotEmptyException e) {
			  throw new Exception("Failed to delete file: " + filename, e);
		  } catch (IOException e) {
			  throw new Exception("File permission error while deleting file: " + filename, e);
		  }
	  }
}
