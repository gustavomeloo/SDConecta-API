package br.com.sdconecta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sdconecta.model.Crm;

public interface CrmRepository extends JpaRepository<Crm, Long>{

}
