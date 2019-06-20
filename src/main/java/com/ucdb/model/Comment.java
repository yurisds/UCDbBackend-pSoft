package com.ucdb.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long comments_id;
	
	@ManyToOne
	@JsonBackReference
	private Disciplina disciplina;
	
	@ManyToOne
	private User user;
	
	@Column(name = "text")
	private String text;
	
	public Comment() {}

	public Comment(Disciplina disciplina, User user, String texto) {
		this.disciplina = disciplina;
		this.user = user;
		this.text = texto;
	}

	//pode ser necessário no futuro
	/*
	 * public long getId() { return this.comments_id; }
	 */

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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

	
	
	
	
}
