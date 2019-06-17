package com.ucdb.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Disciplina;

@Repository
public interface DisciplinaDAO<T, ID extends Serializable> extends JpaRepository<Disciplina, Long> {
	
	@Query(value = "select u.id, u.nome from Disciplina u where u.nome like CONCAT('%',:pdisc,'%')")
	List<List<String>> findBySubString (@Param("pdisc")  String disc);
	
	@Query(value = "select u.id, u.nome from Disciplina u")
	List<List<String>> getAll ();

	Disciplina save(Disciplina product);

	Disciplina findById(long codigo);
}
