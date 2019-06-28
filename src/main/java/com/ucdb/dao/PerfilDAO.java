package com.ucdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Perfil;

@Repository
public interface PerfilDAO extends JpaRepository<Perfil, Long>{

	public Perfil save(Perfil perfil);
	
	public Perfil findById(long id);
	
}
