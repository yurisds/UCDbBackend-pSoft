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

import com.ucdb.exceptions.replyComment.ReplyCommentNotFound;
import com.ucdb.model.ReplyComment;
import com.ucdb.service.ReplyCommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controller de comentários de respostas a outros comentários")
@RestController
@RequestMapping({ "/v1/perfil" })
public class ReplyCommentController {

	@Autowired
	private ReplyCommentService replyCommentService;

	@ApiOperation(value = "Cria um comentário de resposta")
	@PostMapping(value = "/comentar/reply/{idComment}/{email}")
	@ResponseBody
	public ResponseEntity<ReplyComment> replyComment(@PathVariable long idComment, @PathVariable String email,
			@RequestBody ReplyComment reply) {
		ReplyComment r = this.replyCommentService.replyComment(idComment, email, reply);
		if (r == null) {
			throw new ReplyCommentNotFound("Id do comentario pai esta incorreto ou o usuario nao existe");
		}
		return new ResponseEntity<ReplyComment>(r, HttpStatus.OK);
	}

	@ApiOperation(value = "remove um comentário de resposta")
	@PutMapping(value = "/removecommentreply/{idComment}/{idReplyComment}/{idPerfil}/{email}")
	@ResponseBody
	public ResponseEntity<ReplyComment> removeComment(@PathVariable long idComment, @PathVariable long idReplyComment,
			@PathVariable long idPerfil, @PathVariable String email) {
		ReplyComment r = this.replyCommentService.removeReplyComment(idComment, idReplyComment, idPerfil, email);
		if (r == null) {
			throw new ReplyCommentNotFound("Id do comentario pai esta incorreto ou o perfil nao existe");
		} else {
			return new ResponseEntity<ReplyComment>(r, HttpStatus.ACCEPTED);
		}
	}
}
