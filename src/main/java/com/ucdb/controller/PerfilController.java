package com.ucdb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.model.Comment;
import com.ucdb.model.Perfil;
import com.ucdb.model.Rating;
import com.ucdb.model.ReplyComment;
import com.ucdb.service.PerfilService;

@RestController
@RequestMapping({"/v1/perfil"})
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	/*
	 * @PostMapping(value = "/{id}")
	 * 
	 * @ResponseBody public ResponseEntity<Perfil> create(@PathVariable long
	 * id, @RequestBody Perfil perfil) { return new ResponseEntity<>(
	 * this.perfilService.create(perfil, id), HttpStatus.CREATED); }
	 */
	
	@GetMapping(value = "/") 
	@ResponseBody
	public ResponseEntity<List<Perfil>> getAll() {
		List<Perfil> listPerfil = this.perfilService.getAll();
		if (listPerfil == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Perfil>>(listPerfil, HttpStatus.OK);
	}
	
	@GetMapping(value = "/codigo/{codigo}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> getById(@PathVariable long codigo, @PathVariable String email) {
		Perfil perfil = perfilService.getById(codigo, email);
		if (perfil == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
	

	@PostMapping(value = "/curtir/{codigo}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> usuarioCurtiu(@PathVariable long codigo, @PathVariable String email) {
		return new ResponseEntity<Perfil>(this.perfilService.usuarioCurtiu(codigo, email), HttpStatus.OK);
	}

	@PostMapping(value = "/comentar/{codigo}/{email}")
	@ResponseBody
	public ResponseEntity<Comment> usuarioComentou(@PathVariable long codigo, @PathVariable String email,
			@RequestBody Comment comentario) {

		return new ResponseEntity<Comment>(this.perfilService.usuarioComentou(codigo, email, comentario),
				HttpStatus.OK);
	}
	
	@PostMapping(value = "/comentar/reply/{idComment}/{email}")
	@ResponseBody
	public ResponseEntity<ReplyComment> replyComment(@PathVariable long idComment, @PathVariable String email,
			@RequestBody ReplyComment reply) {

		return new ResponseEntity<ReplyComment>(this.perfilService.replyComment(idComment, email, reply),
				HttpStatus.OK);
	}

	@PostMapping(value = "/darnota/{codigo}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> usuarioDeuNota(@PathVariable long codigo, @PathVariable String email,
			@RequestBody Rating rating){
		
		return new ResponseEntity<Perfil>(this.perfilService.usuarioDeuNota(codigo, email, rating),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/removecomment/{idComment}/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<Perfil> removeComment(@PathVariable long idComment, @PathVariable long idPerfil, 
			@PathVariable String email) {
		Perfil p = this.perfilService.removeComment(idComment, idPerfil, email);
		if (p == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Perfil>(p, HttpStatus.ACCEPTED);
		}
	}
	
}
