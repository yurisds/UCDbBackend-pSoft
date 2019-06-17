package com.ucdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Disciplina;

@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {
	
	@Query(value = "select u.id, u.nome from Disciplina u where u.nome like CONCAT('%',:pdisc,'%')")
	List<List<String>> findBySubString (@Param("pdisc")  String disc);
	
	@Query(value = "select u.id, u.nome from Disciplina u")
	List<List<String>> getAll ();
}
