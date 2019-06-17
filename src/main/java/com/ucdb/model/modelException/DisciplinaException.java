package com.ucdb.model.modelException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DisciplinaException extends RuntimeException {

	public DisciplinaException(String msg) {
		super(msg);

	}

}
