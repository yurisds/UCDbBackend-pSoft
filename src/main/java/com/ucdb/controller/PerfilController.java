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
import com.ucdb.model.ReplyComment;
import com.ucdb.service.PerfilService;

@RestController
@RequestMapping({ "/v1/perfil" })
public class PerfilController {

	@Autowired
	private PerfilService perfilService;

	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<Perfil>> getAll() {
		List<Perfil> listPerfil = this.perfilService.getAll();
		if (listPerfil == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Perfil>>(listPerfil, HttpStatus.OK);
	}

	@GetMapping(value = "/likes/")
	@ResponseBody
	public ResponseEntity<List<Perfil>> getAllByLikes() {
		List<Perfil> listPerfil = this.perfilService.getAllByLikes();
		if (listPerfil == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Perfil>>(listPerfil, HttpStatus.OK);
	}

	@GetMapping(value = "/comments/")
	@ResponseBody
	public ResponseEntity<List<Perfil>> getAllByComments() {
		List<Perfil> listPerfil = this.perfilService.getAllByComments();
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

}
