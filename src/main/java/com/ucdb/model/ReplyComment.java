package com.ucdb.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "replyComment")
public class ReplyComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long comments_id;

	private long parent;

	private String text;

	@ManyToOne
	private User user;

	private Date date;

	@ManyToOne
	private Comment commentParent;

	@Transient
	private boolean usuarioComentou;

	private boolean comentarioApagado;

	public ReplyComment() {
	}

	public ReplyComment(long comments_id, long parent, String text) {
		super();
		this.text = text;
		this.comentarioApagado = false;
	}

	public long getComments_id() {
		return comments_id;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user.getEmail();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isUsuarioComentou() {
		return usuarioComentou;
	}

	public Comment getCommentParent() {
		return commentParent;
	}

	public void setCommentParent(Comment commentParent) {
		this.commentParent = commentParent;
	}

	public void setUsuarioComentou(boolean usuarioComentou) {
		this.usuarioComentou = usuarioComentou;
	}

	public boolean isComentarioApagado() {
		return comentarioApagado;
	}

	public void setComentarioApagado(boolean comentarioApagado) {
		this.comentarioApagado = comentarioApagado;
	}

}
