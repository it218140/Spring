package gr.hua.dit.ds.restdemo.controller;

import java.util.List;
import java.util.Optional;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;

import javax.security.auth.Subject;

@RestController
public class SupervisorController {
	//Parameters
	@Autowired
	private SubjectsRepository subjectsRepository;
	//Mappings
	
	//Retrieve a list of all subjects
	@GetMapping("/Subjects")
	public List<Subjects> getSubjects() {
		return subjectsRepository.findAll();
	}
	

	//Retrieve a list of User with id
	@RequestMapping(value = "/Subjects",params = "id")
	public Optional<Subjects> getSubjectsById(@RequestParam int id) {
		
		Optional<Subjects> subjectsOptional=subjectsRepository.findById(id);
		
		if(!subjectsOptional.isPresent())
			throw new AttributeNotFoundException("id-" + id);
		
		
		return subjectsOptional;
	}
	
	//Update postgraduate state("Accepted/Declined") by subject id
	@PutMapping("/Subjects/{id}")
	public ResponseEntity<Object> updateDayoff(@RequestBody Subjects subjs,@PathVariable("id") int id) {

		Optional<Subjects> subjectsOptional = subjectsRepository.findById(id);

		if (!subjectsOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		subjectsOptional.get().setState(subjs.getState());
		
		subjectsRepository.save(subjectsOptional.get());

		return ResponseEntity.noContent().build();
	}

	//Delete subject with id
	@DeleteMapping("/Subjects/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		
		subjectsRepository.deleteById(id);
	}
	
}
