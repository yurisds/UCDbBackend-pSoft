package com.ucdb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentsId;

	@ManyToOne
	@JsonBackReference
	private Perfil perfil;

	@ManyToOne
	private User user;

	@Column(name = "text")
	private String text;

	private Date date;

	@Transient
	private boolean usuarioComentou;

	private boolean comentarioApagado;

	@OneToMany
	private List<ReplyComment> reply;

	public Comment() {
	}

	public Comment(Perfil perfil, User user, String texto) {
		this.perfil = perfil;
		this.user = user;
		this.text = texto;
		this.reply = new ArrayList<ReplyComment>();
		this.comentarioApagado = false;
	}

	public long getId() {
		return commentsId;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setperfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getUser() {
		return user.getEmail();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		if (!isComentarioApagado())
			return text;
		return "O comentario foi apagado";
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isUsuarioComentou() {
		return usuarioComentou;
	}

	public void setUsuarioComentou(boolean usuarioComentou) {
		this.usuarioComentou = usuarioComentou;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isComentarioApagado() {
		return comentarioApagado;
	}

	public void setComentarioApagado(boolean comentarioApagado) {
		this.comentarioApagado = comentarioApagado;
	}

	public List<ReplyComment> getReply() {
		return reply;
	}

	public void setReply(List<ReplyComment> reply) {
		this.reply = reply;
	}
}
