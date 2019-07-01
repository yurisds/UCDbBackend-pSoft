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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Perfil {

	@Id
	private long id;

	@OneToOne
	private Disciplina disciplina;

	@ManyToMany
	@JoinTable(name = "Likes", joinColumns = { @JoinColumn(name = "id_disciplina") }, inverseJoinColumns = {
			@JoinColumn(name = "email_user") })
	private List<User> users;

	@OneToMany
	private List<Comment> comments;

	private long likes;

	private long commentsNumber;

	@Transient
	private boolean usuarioCurtiu;

	public Perfil() {
	}

	public Perfil(List<User> users, Disciplina disciplina) {
		this.disciplina = disciplina;
		this.users = users;
		this.comments = new ArrayList<Comment>();

		this.likes = 0;
		this.commentsNumber = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLikes() {
		if (this.users != null)
			return this.users.size();
		return 0;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addRating(User user) {

	}

	public boolean isUsuarioCurtiu() {
		return usuarioCurtiu;
	}

	public void setUsuarioCurtiu(boolean usuarioCurtiu) {
		this.usuarioCurtiu = usuarioCurtiu;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Comment getCommentById(long idComment) {
		Iterator<Comment> it = this.comments.iterator();
		while (it.hasNext()) {
			Comment c = it.next();
			if (c.getId() == idComment) {
				return c;
			}
		}

		return null;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void addLikes() {
		this.likes++;
	}

	public void removeLikes() {
		this.likes--;
	}

	public void addCommentsNumber() {
		this.commentsNumber++;
	}

	public void removeCommentsNumber() {
		this.commentsNumber--;
	}
}
