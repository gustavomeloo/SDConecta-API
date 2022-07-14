package br.com.sdconecta.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Crm {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 45, message = "O Crm deve ter no máximo 45 caracteres")
	private String crm;
	
	@NotBlank
	@Size(max = 2, message = "O uf deve ter no máximo 2 caracteres")
	private String uf;
	
	@Size(max = 255, message = "A especialidade deve ter no máximo 255 caracteres")
	private String specialty;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
