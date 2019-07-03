package com.ucdb.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.model.User;
import com.ucdb.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controller de login")
@RestController
@RequestMapping("/v1/auth")
public class LoginController {

	private final String TOKEN_KEY = "yuyu";

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Cria um token para o Usuário logar no UCDB")
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody User user) throws ServletException {
		// Recupera o usuario
		System.out.print("POST LOGIN");
		User authUser = userService.findByEmail(user.getEmail());

		// verificacoes
		if (authUser == null) {
			throw new ServletException("Usuario nao encontrado!");
		}

		if (!authUser.Password().equals(user.Password())) {
			throw new ServletException("Senha invalida!");
		}

		String token = Jwts.builder().setSubject(authUser.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000)).compact();

		return new LoginResponse(token);

	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
	}

}
