package br.com.sdconecta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Crm {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 45)
	private String crm;
	
	@NotBlank
	@Size(max = 2)
	private String uf;
	
	@Size(max = 255)
	private String specialty;
	
	@ManyToOne
	private User user;

}
