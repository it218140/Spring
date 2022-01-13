package gr.hua.dit.ds.restdemo.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.http.MediaType;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.entity.User;
import gr.hua.dit.ds.restdemo.repositiry.AdminRepository;
import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;

@RestController
public class UserController {
	
	private final List<String> type = Arrays.asList("special purpose","disease","normal","student","agricultural","work strike");
	
	@Autowired
	private SubjectsRepository subjectsRepository;
	
	@Autowired AdminRepository AdminRepository;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/User",produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Subjects> getSubjects(Authentication authentication) {
		User user=new User();
		user.setUsername(authentication.getName());
		List<Subjects> subjects=subjectsRepository.findByUsername(user);
		if(!subjects.isEmpty()) {
			System.out.println(subjects.get(0).getUser().getUsername());
		}
		return subjects;
	}

}

