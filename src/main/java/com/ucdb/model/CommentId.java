package com.ucdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentId implements Serializable{

	@Column(name = "disciplina_id")
	private long disciplinaID;
	
	@Column(name = "user_email")
	private String userEmail;
	
	public CommentId() {}

	public CommentId(long disciplinaID, String userEmail) {
		this.disciplinaID = disciplinaID;
		this.userEmail = userEmail;
	}

	public long getDisciplinaID() {
		return disciplinaID;
	}

	public void setDisciplinaID(long disciplinaID) {
		this.disciplinaID = disciplinaID;
	}

	public String getUserID() {
		return userEmail;
	}

	public void setUserID(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (disciplinaID ^ (disciplinaID >>> 32));
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
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
		CommentId other = (CommentId) obj;
		if (disciplinaID != other.disciplinaID)
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

	
	
}
