package com.ucdb.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class LikesIdentity implements Serializable{

	@NotNull
	private long idDisciplina;
	
	@NotNull
	private String emailUser;

	public LikesIdentity(long idDisciplina, String emailUser) {
		this.idDisciplina = idDisciplina;
		this.emailUser = emailUser;
	}

	public LikesIdentity() {
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	
}
