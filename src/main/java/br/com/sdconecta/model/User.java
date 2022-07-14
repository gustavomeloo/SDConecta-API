package br.com.sdconecta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "Profile", uniqueConstraints={@UniqueConstraint(columnNames={"email"}), })
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email inválido")
	private String email;
	
	@NotBlank
	@Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
	private String password;
	
	@NotBlank
	@Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
	private String name;
	
	@Size(max = 255, message = "O Sobrenome deve ter no máximo 255 caracteres")
	private String surname;
	
	@Size(max = 255, message = "O Telefone deve ter no máximo 255 caracteres")
	private String phone;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Crm> crms = new ArrayList<>();
	
		

}
