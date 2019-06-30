package com.ucdb.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.CommentDAO;
import com.ucdb.dao.PerfilDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Comment;
import com.ucdb.model.Perfil;
import com.ucdb.model.ReplyComment;
import com.ucdb.model.User;

@Service
public class CommentService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDao;

	@Autowired
	private PerfilDAO perfilDAO;

	public Comment insertComment(long id, String email, Comment comentario) {
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
}
