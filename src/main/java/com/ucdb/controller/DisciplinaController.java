package com.ucdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;
import com.ucdb.model.Rating;
import com.ucdb.service.DisciplinaService;
import com.ucdb.service.PerfilService;

@RestController
@RequestMapping({ "/v1/disciplinas" })
public class DisciplinaController {

	private DisciplinaService disciplinaService;
	
	@Autowired
	private PerfilService perfilService;

	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Disciplina> create(@RequestBody Disciplina nome) {
		Disciplina newDisciplina = disciplinaService.create(nome);
		if (newDisciplina == null) {
			throw new InternalError("Something went wrong");
		}
		Perfil newPerfil = perfilService.create(newDisciplina.getId());
		return new ResponseEntity<Disciplina>(newDisciplina, HttpStatus.CREATED);

	}
	
	@PostMapping(value = "/all")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> createAll(@RequestBody List<Disciplina> disciplinas) {
		List listDisciplinas = this.disciplinaService.createAll(disciplinas);
		if (listDisciplinas == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<List<Disciplina>>(listDisciplinas, HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "/{substring}")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> findBySubString(@PathVariable String substring) {
		return new ResponseEntity<List<Disciplina>>(disciplinaService.findBySubString(substring), HttpStatus.OK);
	}

	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> getAll() {
		List disciplina = disciplinaService.getAll();
		return new ResponseEntity<List<Disciplina>>(disciplina, HttpStatus.OK);
	}
}
