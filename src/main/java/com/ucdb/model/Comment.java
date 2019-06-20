package com.ucdb.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "Comment")
@Table(name = "comment")
public class Comment {

	@EmbeddedId
	private CommentId id;
	
	@ManyToOne
	@MapsId("disciplinaID")
	private Disciplina disciplina;
	
	@ManyToOne
	@MapsId("userID")
	private User user;
	
	@Column(name = "text")
	private String text;
	
	public Comment() {}

	public Comment(Disciplina disciplina, User user) {
		this.disciplina = disciplina;
		this.user = user;
		this.id = new CommentId(disciplina.getId(), user.getEmail());
	}

	public CommentId getId() {
		return id;
	}

	public void setId(CommentId id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public User getUser() {
		return user;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
