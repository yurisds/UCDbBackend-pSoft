package com.ucdb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.exceptions.user.UserNotFoundException;
import com.ucdb.model.User;
import com.ucdb.service.UserService;

@RestController
@RequestMapping({ "/v1/users" })
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/private")
	public String privateMsg() {
		return "Mensagem privada";
	}

	@GetMapping("/public")
	public String publicMsg() {
		return "Mensagem publica";
	}

	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		User newUser = userService.create(user);
		if (newUser == null) {
			throw new InternalError("Something went wrong");
		}
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{email}")
	@ResponseBody
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("Email nao cadastrado");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<List<User>> getAll() {
		List user = userService.findAll();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}



}
