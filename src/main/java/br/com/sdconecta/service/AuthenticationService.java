package br.com.sdconecta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sdconecta.model.User;
import br.com.sdconecta.repository.UserRepository;


@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository u;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = u.findByEmail(username);
		if (user.isEmpty()) throw new UsernameNotFoundException("Authentication.user");
		return user.get();
	}
	
	public static PasswordEncoder getPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}
}
