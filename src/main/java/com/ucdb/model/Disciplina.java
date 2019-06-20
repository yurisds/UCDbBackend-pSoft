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
	private List<Comment> comments = new ArrayList<>();

	public Disciplina() {
	}

	public Disciplina(String nome) {
		this.nome = nome;
		this.users = new ArrayList<>();
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

	public void addComment(User user) {
		Comment comment = new Comment(this, user);
		comments.add(comment);
		user.getComments().add(comment);
	}
	
	public void removeComment(User user) {
		for (Iterator<Comment> iterator = comments.iterator(); iterator.hasNext();) {
			Comment comment = iterator.next();
			if (comment.getDisciplina().equals(this) && comment.getUser().equals(user)) {
				iterator.remove();
				comment.getUser().getComments().remove(comment);
				comment.setDisciplina(null);
				comment.setUser(null);
			}
			
		}
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
