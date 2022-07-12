package br.com.sdconecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
