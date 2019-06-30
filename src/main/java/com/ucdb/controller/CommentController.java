package com.ucdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.model.Comment;
import com.ucdb.service.CommentService;

@RestController
@RequestMapping({ "/v1/perfil" })
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping(value = "/comentar/{codigo}/{email}")
	@ResponseBody
	public ResponseEntity<Comment> insertComment(@PathVariable long codigo, @PathVariable String email,
			@RequestBody Comment comentario) {
		Comment c = this.commentService.insertComment(codigo, email, comentario);
		if (c == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Comment>(c, HttpStatus.ACCEPTED);
		}
	}

	@PutMapping(value = "/removecomment/{idComment}/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<Comment> removeComment(@PathVariable long idComment, @PathVariable long idPerfil,
			@PathVariable String email) {
		Comment c = this.commentService.removeComment(idComment, idPerfil, email);
		if (c == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Comment>(c, HttpStatus.ACCEPTED);
		}
	}

}
