package gr.hua.dit.ds.restdemo.controller;

import java.util.List;

import gr.hua.dit.ds.restdemo.entity.Subjects;
import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.restdemo.entity.Subjects;

import gr.hua.dit.ds.restdemo.repositiry.SubjectsRepository;

@RestController
public class ManagerController {
	// Parameters
	
	@Autowired
	private SubjectsRepository ManagerRepository;
	
	//Mappings

	@GetMapping("/Manager")
	public List<Subjects> retrieveAllSubjects() {
		return ManagerRepository.findAll();
	}
	
	//Retrive a list of all subjects with id parameter
	@GetMapping("/Manager/{state}")
	public List<Subjects> retreiveByUsername(@PathVariable String id) {
		
		List<Subjects> subjs = ManagerRepository.findByid(id);
		
		if (subjs.isEmpty())
			throw new AttributeNotFoundException("id-" + id);
		
		
		return subjs;
	}
	
	
}
