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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sdconecta.model.Crm;
import br.com.sdconecta.repository.CrmRepository;


@RestController
@RequestMapping("/api/crm")
public class ApiCrmController {
	
	@Autowired
	private CrmRepository c;
	
	@GetMapping()
	@Cacheable("crms")
	public Page<Crm> index( @PageableDefault Pageable pageable){
		return c.findAll(pageable);
	}
	
	@PostMapping()
	@CacheEvict(value = "crms", allEntries = true)
	public ResponseEntity<Crm> create(@RequestBody @Valid Crm crm, UriComponentsBuilder uriBuilder){
		c.save(crm);
		URI uri = uriBuilder.path("/api/crm/{id}").buildAndExpand(crm.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Crm> get(@PathVariable long id){
		return ResponseEntity.of(c.findById(id));
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "crms", allEntries = true)
	public ResponseEntity<Crm> delete(@PathVariable long id){
		Optional<Crm> crm = c.findById(id);
		if(crm.isEmpty()) return ResponseEntity.notFound().build();
		c.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "crms", allEntries = true)
	public ResponseEntity<Crm> update(@PathVariable long id, @RequestBody @Valid Crm crm){
		Optional<Crm> crm1 = c.findById(id);
		if (crm1.isEmpty()) return ResponseEntity.notFound().build();
		crm.setId(id);
		c.save(crm);
		return ResponseEntity.ok(crm);
	}
	
}
