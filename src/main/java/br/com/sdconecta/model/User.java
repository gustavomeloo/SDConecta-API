package br.com.sdconecta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{user.email.invalid}")
	private String email;
	
	@NotBlank
	@Size(max = 255, message = "{user.password.size}")
	private String password;
	
	@NotBlank
	@Size(max = 255, message = "{user.password.size}")
	private String name;
	
	@Size(max = 255, message = "{user.password.size}")
	private String surname;
	
	@Size(max = 255, message = "{user.password.size}")
	private String phone;
	
		

}
