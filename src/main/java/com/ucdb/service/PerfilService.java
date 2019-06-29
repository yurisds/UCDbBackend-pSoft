package com.ucdb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.CommentDAO;
import com.ucdb.dao.DisciplinaDAO;
import com.ucdb.dao.PerfilDAO;
import com.ucdb.dao.RatingDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;
import com.ucdb.model.Rating;
import com.ucdb.model.Rating_Id;
import com.ucdb.model.User;

@Service
public class PerfilService {

	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private DisciplinaDAO disciplinaDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private RatingDAO ratingDao;

	public Perfil create(long id) {
		Disciplina d = this.disciplinaDAO.findById(id);
		Perfil perfil = new Perfil();
		perfil.setId(id);
		perfil.setDisciplina(d);

		return this.perfilDAO.save(perfil);
	}

	public Perfil getById(long codigo, String email) {
		Perfil p = this.perfilDAO.findById(codigo);
		User u = this.userDAO.findByEmail(email);
		if (p.getUsers().contains(u)) {
			p.setUsuarioCurtiu(true);
		} else {
			p.setUsuarioCurtiu(false);
		}

		for (Comment c : p.getComments()) {
			if (c.getUser().equals(u.getEmail())) {
				c.setUsuarioComentou(true);
			} else {
				c.setUsuarioComentou(false);
			}
		}

		return p;
	}

	public Perfil usuarioCurtiu(long id, String email) {
		User u = this.userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (!p.getUsers().contains(u)) {
			p.getUsers().add(u);
		} else {
			p.getUsers().remove(u);
		}
		return this.perfilDAO.save(p);

	}

	public Comment  usuarioComentou(long id, String email, Comment comentario) {
		User u = this.userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		
		if (p != null && u != null) {
			comentario.setperfil(p);
			comentario.setUser(u);
			comentario.setDate(new Date());
			
			p.getComments().add(comentario);

			return this.commentDao.save(comentario);
		}else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public Perfil usuarioDeuNota(long id, String email, Rating rating) {
		User u = this.userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		
		
		if (p != null && u != null) {
			rating.setDisciplina(p);
			rating.setUser(u);
			rating.setRatingId(new Rating_Id(email, id));
			Rating r = this.ratingDao.save(rating);
			List<Rating> l = this.ratingDao.findByPerfil(p);
			p.setRatings(l);

			return this.perfilDAO.save(p);
		}else {
			throw new IllegalArgumentException();
		}
		
	}
}
