package com.ucdb.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

	private ZonedDateTime date;

	@Column(name = "text")
	private String text;

	@OneToMany
	private List<ReplyComment> reply;

	private boolean comentarioApagado;

	@Transient
	private boolean usuarioComentou;

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

	public User getUser() {
		return this.user;
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

	public String getDate() {
		DateTimeFormatter formatador = DateTimeFormatter
				  .ofLocalizedDateTime(FormatStyle.SHORT)
				  .withLocale(new Locale("pt", "br"));

		return date.format(formatador);
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public boolean isComentarioApagado() {
		return comentarioApagado;
	}

	public void setComentarioApagado(boolean comentarioApagado) {
		this.comentarioApagado = comentarioApagado;
	}

	public List<ReplyComment> getReply() {
		if (!isComentarioApagado())
			return reply;
		return new ArrayList<ReplyComment>();
	}

	public void setReply(List<ReplyComment> reply) {
		this.reply = reply;
	}

	public ReplyComment getReplyCommentById(long idComment) {
		Iterator<ReplyComment> it = this.reply.iterator();
		while (it.hasNext()) {
			ReplyComment r = it.next();
			if (r.getComments_id() == idComment) {
				return r;
			}
		}

		return null;
	}
}
