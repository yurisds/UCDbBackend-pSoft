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

import com.ucdb.exceptions.disciplina.DisciplinaNotFound;
import com.ucdb.exceptions.email.EmailNotFound;
import com.ucdb.model.Comment;
import com.ucdb.model.Disciplina;
import com.ucdb.model.Perfil;
import com.ucdb.service.DisciplinaService;
import com.ucdb.service.PerfilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controller de Disciplinas")
@RestController
@RequestMapping({ "/v1/disciplinas" })
public class DisciplinaController {

	private DisciplinaService disciplinaService;

	@Autowired
	private PerfilService perfilService;

	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	@ApiOperation(value = "Cria uma nova disciplina com o seu respectivo perfil")
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Disciplina> create(@RequestBody Disciplina nome) {
		Disciplina newDisciplina = disciplinaService.create(nome);
		if (newDisciplina == null) {
			throw new DisciplinaNotFound("Valores passados nao podem ser null!");
		}
		Perfil newPerfil = perfilService.create(newDisciplina.getId());
		return new ResponseEntity<Disciplina>(newDisciplina, HttpStatus.CREATED);

	}

	// Only for deploy
	@ApiOperation(value = "Cria novas disciplinas com os seus respectivos perfis")
	@PostMapping(value = "/all")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> createAll(@RequestBody List<Disciplina> disciplinas) {
		List listDisciplinas = this.disciplinaService.createAll(disciplinas);
		if (listDisciplinas == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} else {
			List<Perfil> newListPerfil = this.perfilService.createAll(disciplinas);
			return new ResponseEntity<List<Disciplina>>(listDisciplinas, HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Retorna todas as disciplinas com seus respectivos IDs buscando através da substring")
	@GetMapping(value = "/{substring}")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> findBySubString(@PathVariable String substring) {
		return new ResponseEntity<List<Disciplina>>(disciplinaService.findBySubString(substring), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna todas as disciplinas com seus respectivos IDs")
	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<Disciplina>> getAll() {
		List disciplina = disciplinaService.getAll();
		return new ResponseEntity<List<Disciplina>>(disciplina, HttpStatus.OK);
	}
}
