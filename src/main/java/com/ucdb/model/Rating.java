package com.ucdb.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "rating")
public class Rating {

	@EmbeddedId
	private Rating_Id ratingId;

	@ManyToOne

	@JsonBackReference

	@MapsId("disciplinaId")
	private Disciplina disciplina;

	@ManyToOne

	@MapsId("userEmail")
	private User user;

	private float valor;

	public Rating() {}

	public Rating(Disciplina disciplina, User user, float valor) {
		if (disciplina == null || user == null) {
			this.ratingId = new Rating_Id();
		} else {
			this.ratingId = new Rating_Id(user.getEmail(), disciplina.getId());
		}
		this.disciplina = disciplina;
		this.user = user;
		this.valor = valor;
	}
	
	public Rating_Id getRatingId() {
		return ratingId;
	}

	public void setRatingId(Rating_Id ratingId) {
		this.ratingId = ratingId;
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

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
