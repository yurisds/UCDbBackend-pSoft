package com.ucdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Rating_Id implements Serializable{

	
	  @Column(name = "user") private String userEmail;
	  
	  @Column(name = "disciplinaId") private long disciplinaId;
	  
	  
	  public Rating_Id() {}
	  
	  
	  public Rating_Id(String userEmail, long disciplinaId) { this.userEmail =
	  userEmail; this.disciplinaId = disciplinaId; }
	  
	  
	  public String getUserEmail() { return userEmail; }
	  
	  
	  public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
	  
	  
	  public long getDisciplinaId() { return disciplinaId; }
	  
	  
	  public void setDisciplinaId(long disciplinaId) { this.disciplinaId =
	  disciplinaId; }
	 
	
//	@ManyToOne
//	private User user;
//	@ManyToOne
//	private Disciplina disciplina;
	
	/*
	 * public Rating_Id(User user, Disciplina disciplina) { this.user = user;
	 * this.disciplina = disciplina; }
	 * 
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 * 
	 * public Disciplina getDisciplina() { return disciplina; }
	 * 
	 * public void setDisciplina(Disciplina disciplina) { this.disciplina =
	 * disciplina; }
	 */
	
}
