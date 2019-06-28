package com.ucdb.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;
import com.ucdb.model.Rating;
import com.ucdb.model.Rating_Id;

@Repository
public interface RatingDAO extends CrudRepository<Rating, Rating_Id> {

	Rating save(Rating r);
	
	List<Rating> findByPerfil(Perfil perfil);
	
}
