package com.ucdb.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nome;

	@ManyToMany
	@JoinTable(name = "Likes", joinColumns = { @JoinColumn(name = "id_disciplina") }, inverseJoinColumns = {
			@JoinColumn(name = "email_user") })
	private List<User> users;
	
	@OneToMany
	private List<Comment> comments;

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Disciplina() {
	}

	public Disciplina(String nome) {
		this.nome = nome;
		this.users = new ArrayList<>();
		this.comments = new ArrayList<Comment>();
	}

	public int getLikes() {
		if (this.users != null)
			return this.users.size();
		return 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String disciplina) {
		this.nome = disciplina;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	
}
