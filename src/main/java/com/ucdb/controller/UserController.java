package com.ucdb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{email}")
	@ResponseBody
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

}
