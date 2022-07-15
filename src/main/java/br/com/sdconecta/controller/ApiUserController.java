package br.com.sdconecta.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sdconecta.model.User;
import br.com.sdconecta.repository.UserRepository;
import br.com.sdconecta.service.AuthenticationService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserRepository u;
	
	@GetMapping()
	@Cacheable("users")
	public Page<User> index(@RequestParam(required = false) String name, String specialty, @PageableDefault Pageable pageable){
		if(name != null) {
			return u.findByNameLike('%' + name + '%', pageable);
		}
		
		if(specialty != null) {
			return u.findBySpecialty(specialty, pageable);
		}
		
		return u.findAll(pageable);
	}
	
	@PostMapping()
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder){
		user.setPassword(AuthenticationService.getPasswordEnconder().encode(user.getPassword()));
		u.save(user);
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> get(@PathVariable long id){
		return ResponseEntity.of(u.findById(id));
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<User> delete(@PathVariable long id){
		Optional<User> user = u.findById(id);
		if(user.isEmpty()) return ResponseEntity.notFound().build();
		u.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<User> update(@PathVariable long id, @RequestBody @Valid User user){
		Optional<User> user1 = u.findById(id);
		if (user1.isEmpty()) return ResponseEntity.notFound().build();
		user.setId(id);
		u.save(user);
		return ResponseEntity.ok(user);
	}
	

}
