package com.ucdb.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Likes {

	@EmbeddedId
	private LikesIdentity likesIdentity;
}
