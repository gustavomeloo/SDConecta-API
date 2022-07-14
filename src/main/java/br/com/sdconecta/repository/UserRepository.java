package br.com.sdconecta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sdconecta.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Page<User> findByNameLike(String name, Pageable pageable);
	
	@Query("select u from User u inner join Crm c on c.user = u.id where c.specialty like CONCAT('%', ?1, '%') ")
	Page<User> findBySpecialty(String specialty, Pageable pageable);

}
