package com.ucdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.CommentDAO;
import com.ucdb.dao.DisciplinaDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.User;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaDAO disciplinaDao;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDao;

	public Disciplina create(Disciplina disciplina) {
		return this.disciplinaDao.save(disciplina);
	}

	public List<List<String>> findBySubString(String disciplina) {
		return this.disciplinaDao.findBySubString(disciplina);
	}

	public List<List<String>> getAll() {
		return this.disciplinaDao.getAll();
	}

	public Disciplina getById(long codigo) {
		Disciplina d = this.disciplinaDao.findById(codigo);
		return this.disciplinaDao.findById(codigo);
	}

	public Disciplina usuarioCurtiu(long id, String email) {
		User u = this.userDAO.findByEmail(email);
		Disciplina d = this.disciplinaDao.findById(id);
		if (!d.getUsers().contains(u)) {
			d.getUsers().add(u);
		} else {
			d.getUsers().remove(u);
		}
		return this.disciplinaDao.save(d);

	}

	public Disciplina usuarioComentou(long id, String email, Comment comentario) {
		User u = this.userDAO.findByEmail(email);
		Disciplina d = this.disciplinaDao.findById(id);

		if (d != null && u != null) {
			comentario.setDisciplina(d);
			comentario.setUser(u);
			Comment c = this.commentDao.save(comentario);
			List<Comment> l = commentDao.findByDisciplina(d);
			d.setComments(l);

			return this.disciplinaDao.save(d);
		}else {
			throw new IllegalArgumentException();
		}
		
	}

}
