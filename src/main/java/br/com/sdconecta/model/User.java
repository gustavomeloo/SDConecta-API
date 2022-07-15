package br.com.sdconecta.model;

import java.util.ArrayList;
import java.util.Collection;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "Profile", uniqueConstraints={@UniqueConstraint(columnNames={"email"}), })
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
		

}
