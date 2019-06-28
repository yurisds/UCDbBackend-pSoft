package com.ucdb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long comments_id;

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

	public Comment() {
	}

	public Comment(Perfil perfil, User user, String texto) {
		this.perfil = perfil;
		this.user = user;
		this.text = texto;
	}

	// pode ser necessário no futuro
	/*
	 * public long getId() { return this.comments_id; }
	 */

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
		return text;
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

}
