package com.ucdb.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;

@Repository
public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Long> {
	
	public Comment save(Comment c); 

	public List<Comment> findByPerfil(Perfil p);
	
	public Comment findById(long id);
}
