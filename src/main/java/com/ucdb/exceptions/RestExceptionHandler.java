package com.ucdb.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ucdb.exceptions.comments.CommentNotFound;
import com.ucdb.exceptions.disciplina.DisciplinaNotFound;
import com.ucdb.exceptions.email.EmailNotFound;
import com.ucdb.exceptions.replyComment.ReplyCommentNotFound;
import com.ucdb.exceptions.user.UserNotFoundException;


public class RestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomRestError> handleAnyException(Exception ex, WebRequest request) {
		CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CustomRestError> notFound(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(EmailNotFound.class)
	public ResponseEntity<CustomRestError> emailNotFound(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(DisciplinaNotFound.class)
	public ResponseEntity<CustomRestError> DisciplinaNotFound(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<CustomRestError> CommentNotFound(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ReplyCommentNotFound.class)
	public ResponseEntity<CustomRestError> ReplyCommentNotFound(Exception ex, WebRequest request) {
       CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
