package com.ucdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.config.EmailController;
import com.ucdb.exceptions.user.UserNotFoundException;
import com.ucdb.model.User;
import com.ucdb.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Os Métodos findByEmail e getAll foram criados apenas para testes. serão removidos depois

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Controller de Usuários")
@RestController
@RequestMapping({ "/v1/users" })
public class UserController {

	private UserService userService;

	@Autowired
	private EmailController emailController;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Cria um novo usuário")
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		User newUser = userService.create(user);
		if (newUser == null) {
			throw new InternalError("Something went wrong");
		}
		this.emailController.sendMail(user.getEmail());

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Remove um usuário")
	@DeleteMapping(value = "/{email}")
	public ResponseEntity delete(@RequestParam("email") String email) {
		try {
			userService.delete(email);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}

	}

	@ApiOperation(value = "Busca um usuário pelo seu email")
	@GetMapping(value = "/{email}")
	@ResponseBody
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("Email nao cadastrado");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@ApiOperation(value = "Retorna todos os usuários")
	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<User>> getAll() {
		List user = userService.findAll();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

}
