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
	private Perfil perfil;

	@ManyToOne
	@MapsId("userEmail")
	private User user;

	private float valor;

	public Rating() {}

	public Rating(Perfil perfil, User user, float valor) {
		if (perfil == null || user == null) {
			this.ratingId = new Rating_Id();
		} else {
			this.ratingId = new Rating_Id(user.getEmail(), perfil.getId());
		}
		this.perfil = perfil;
		this.user = user;
		this.valor = valor;
	}
	
	public Rating_Id getRatingId() {
		return ratingId;
	}

	public void setRatingId(Rating_Id ratingId) {
		this.ratingId = ratingId;
	}

	public Perfil getDisciplina() {
		return perfil;
	}

	public void setDisciplina(Perfil perfil) {
		this.perfil = perfil;
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