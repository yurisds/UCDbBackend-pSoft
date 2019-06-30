package com.ucdb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.CommentDAO;
import com.ucdb.dao.DisciplinaDAO;
import com.ucdb.dao.PerfilDAO;
import com.ucdb.dao.RatingDAO;
import com.ucdb.dao.ReplyCommentDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;
import com.ucdb.model.Rating;
import com.ucdb.model.Rating_Id;
import com.ucdb.model.ReplyComment;
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
	
	@Autowired
	private ReplyCommentDAO replyCommentDao;

	public Perfil create(long id) {
		Disciplina d = this.disciplinaDAO.findById(id);
		Perfil perfil = new Perfil();
		perfil.setId(id);
		perfil.setDisciplina(d);

		return this.perfilDAO.save(perfil);
	}
	
	public List<Perfil> createAll(List<Disciplina> disciplinas) {
		List<Perfil> listPerfil = new ArrayList<Perfil>();
		Iterator<Disciplina> it = disciplinas.iterator();
		while (it.hasNext()) {
			Disciplina d = it.next();
			d = this.disciplinaDAO.findById(d.getId());
			Perfil p = new Perfil();
			p.setId(d.getId());
			p.setDisciplina(d);
			listPerfil.add(p);
		}
		return this.perfilDAO.saveAll(listPerfil);
	}

	public Perfil getById(long codigo, String email) {
		Perfil p = this.perfilDAO.findById(codigo);
		User u = this.userDAO.findByEmail(email);
		if (u != null && p!= null) {
			if (p.getUsers().contains(u)) {
				p.setUsuarioCurtiu(true);
			} else {
				p.setUsuarioCurtiu(false);
			}

			for (Comment c : p.getComments()) {
				for(ReplyComment r: c.getReply()) {
					if (r.getUser().equals(u.getEmail())) {
						r.setUsuarioComentou(true);
					} else {
						r.setUsuarioComentou(false);
					}
				}

				if (c.getUser().equals(u.getEmail())) {
					c.setUsuarioComentou(true);
				} else {
					c.setUsuarioComentou(false);
				}
			}
		}

		return p;
	}

	public Perfil usuarioCurtiu(long id, String email) {
		User u = this.userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);
		if (!p.getUsers().contains(u)) {
			p.getUsers().add(u);
			p.addLikes();
		} else {
			p.getUsers().remove(u);
			p.removeLikes();
		}
		return this.perfilDAO.save(p);

	}

	public Comment usuarioComentou(long id, String email, Comment comentario) {
		User u = this.userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(id);

		if (p != null && u != null) {
			comentario.setperfil(p);
			comentario.setUser(u);
			comentario.setDate(new Date());

			p.getComments().add(comentario);

			p.addCommentsNumber();
			return this.commentDao.save(comentario);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public ReplyComment replyComment(long commentParent, String email, ReplyComment r) {
		Comment c = commentDao.findById(commentParent);
		User u = userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(c.getPerfil().getId());
		if (c != null && u != null) {
			p.addCommentsNumber();
			r.setParent(commentParent);
			r.setUser(u);
			r.setDate(new Date());
			c.getReply().add(r);
			return replyCommentDao.save(r);
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
		} else {
			throw new IllegalArgumentException();
		}

	}
	
	public Comment removeComment(long idComment, long idPerfil, String email) {
		Perfil p = this.perfilDAO.findById(idPerfil);
		Comment c = p.getCommentById(idComment);
		
		if (c != null && p != null && c.getUser().equals(email)) {
			for (ReplyComment r : c.getReply()) {
				p.removeCommentsNumber();
			}
			c.setComentarioApagado(true);
			p.removeCommentsNumber();
			return this.commentDao.save(c);
		} else {
			return null;
		}
	}
	
	public ReplyComment removeReplyComment(long idComment, long idReplyComment, long idPerfil, String email) {
		Perfil p = this.perfilDAO.findById(idPerfil);
		Comment c = p.getCommentById(idComment);
		ReplyComment r = c.getReplyCommentById(idReplyComment);
		
		if (r != null && p != null && c != null && r.getUser().equals(email)) {
			r.setComentarioApagado(true);
			p.removeCommentsNumber();
			return this.replyCommentDao.save(r);
		} else {
			return null;
		}
	}
	
	
	public List<Perfil> getAll() {
		return this.perfilDAO.findAll();
	}
	
	public List<Perfil> getAllByLikes() {
		return this.perfilDAO.findAllByLikes();
	}

	public List<Perfil> getAllByComments() {
		return this.perfilDAO.findAllByComments();
	}
	
}
