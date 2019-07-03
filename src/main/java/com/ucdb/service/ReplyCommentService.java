package com.ucdb.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucdb.dao.CommentDAO;
import com.ucdb.dao.PerfilDAO;
import com.ucdb.dao.ReplyCommentDAO;
import com.ucdb.dao.UserDAO;
import com.ucdb.model.Comment;
import com.ucdb.model.Perfil;
import com.ucdb.model.ReplyComment;
import com.ucdb.model.User;

@Service
public class ReplyCommentService {

	@Autowired
	private PerfilDAO perfilDAO;

	@Autowired
	private ReplyCommentDAO replyCommentDao;

	@Autowired
	private CommentDAO commentDao;

	@Autowired
	private UserDAO userDAO;

	public ReplyComment replyComment(long commentParent, String email, ReplyComment r) {
		Comment c = commentDao.findById(commentParent);
		User u = userDAO.findByEmail(email);
		Perfil p = this.perfilDAO.findById(c.getPerfil().getId());
		if (c != null && u != null) {
			p.addCommentsNumber();
			r.setParent(commentParent);
			r.setUser(u);
			ZoneId fuso = ZoneId.of("America/Sao_Paulo");
			r.setDate(ZonedDateTime.now(fuso));
			c.getReply().add(r);
			return replyCommentDao.save(r);
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
}
