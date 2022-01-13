package gr.hua.dit.ds.restdemo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import gr.hua.dit.ds.restdemo.WebSecurityConfig;
import gr.hua.dit.ds.restdemo.entity.User;
import gr.hua.dit.ds.restdemo.entity.UserDetails;
import gr.hua.dit.ds.restdemo.repositiry.AdminRepository;
import gr.hua.dit.ds.restdemo.repositiry.AuthorityRepository;

@RestController
public class AdminController {
	// Parameters
	@Autowired
	private WebSecurityConfig WebSecurityConfig;

	@Autowired
	private AdminRepository AdminRepository;

	@Autowired
	private AuthorityRepository AuthorityRepository;

	//Mappings
	
	// Retreive list of all users
	@GetMapping("/Users")
	public List<User> retrieveAllUsers() {
		
		return AdminRepository.findAll();
	}

	// retrieve User by username
	// if not found throw exception
	@GetMapping("/Users/{username}")
	public User retrieveUser(@PathVariable String username) {
		
		Optional<User> User = AdminRepository.findById(username);

		if (!User.isPresent())
			throw new AttributeNotFoundException("username-" + username);

		// System.out.println(User.get().getAuthorities());
		return User.get();
	}

	// delete User by username
	@DeleteMapping("/Users/{username}")
	public void deleteUser(@PathVariable String username) {
		
		AdminRepository.deleteById(username);
		// System.out.println("OK");
	}

	// Create new user
	@PostMapping("/Users")
	public ResponseEntity<Object> createUser(@RequestBody User User) {

		User.setPassword(WebSecurityConfig.passwordEncoder().encode(User.getPassword()));
		if (User.containsDetails()) {//
			
			User.setUserDetails(new UserDetails(User.getUsername()));
			
		}
		User savedUser = AdminRepository.save(User);
		System.out.println("User id " + savedUser.getUsername());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUsername()).toUri();

		return ResponseEntity.created(location).build();

	}


	@PutMapping("/Users/{username}")
	public ResponseEntity<Object> updateUser(@RequestBody User User, @PathVariable String username) {
		// if not found throw 404
		Optional<User> UserOptional = AdminRepository.findById(username);

		if (!UserOptional.isPresent())
			return ResponseEntity.notFound().build();

		User.setUsername(username);

		if (!User.getPassword().isEmpty()) {
			
			User.setPassword(WebSecurityConfig.passwordEncoder().encode(User.getPassword()));
			System.out.println("Password changed");
			
		} else {
			
			User.setPassword(UserOptional.get().getPassword());
		
		}
		User.setUserDetails(UserOptional.get().getUserDetails());
		

		AuthorityRepository.DeleteByUsername(username);
		
		System.out.println("Replacing authorities");
		

		AdminRepository.save(User);

		return ResponseEntity.noContent().build();
	}

}
